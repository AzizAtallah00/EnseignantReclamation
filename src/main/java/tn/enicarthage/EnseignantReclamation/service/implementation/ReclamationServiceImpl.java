package tn.enicarthage.EnseignantReclamation.service.implementation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.enicarthage.EnseignantReclamation.Enum.status;
import tn.enicarthage.EnseignantReclamation.Enum.urgence;
import tn.enicarthage.EnseignantReclamation.dto.ReclamationDto;
import tn.enicarthage.EnseignantReclamation.exception.EntityNotFoundException;
import tn.enicarthage.EnseignantReclamation.exception.ErrorCodes;
import tn.enicarthage.EnseignantReclamation.exception.InvalidEntityException;
import tn.enicarthage.EnseignantReclamation.model.Enseignant;
import tn.enicarthage.EnseignantReclamation.model.Reclamation;
import tn.enicarthage.EnseignantReclamation.repository.EnseignantRepository;
import tn.enicarthage.EnseignantReclamation.repository.ReclamationRepository;
import tn.enicarthage.EnseignantReclamation.service.ReclamationService;
import tn.enicarthage.EnseignantReclamation.validator.EnseignantValidator;
import tn.enicarthage.EnseignantReclamation.validator.ReclamationValidaor;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class ReclamationServiceImpl implements ReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;
    @Autowired
    private MailSenderService mailsenderservice;
    @Autowired
    private EnseignantRepository enseignantRepository;
    public static final String SECRET ="5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T extractClaim(String token, Function<Claims,T > claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return (T) claimsResolver.apply(claims);
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public ReclamationServiceImpl (ReclamationRepository rec){
        this.reclamationRepository = rec;
    }
    @Override
    public ReclamationDto save(ReclamationDto rec, String token) {
        ReclamationValidaor recv = new ReclamationValidaor();
        final String username = extractUsername(token);
        List <String> l = recv.validate(rec);
        if (l.isEmpty()) {
            rec.setDatecreation(new Date());
            rec.setStatus(status.en_attente);
            Enseignant en = new Enseignant();
            Optional <Enseignant> enopt =enseignantRepository.findById(Integer.valueOf(username));
            en = enopt.orElse(null);
            rec.setEnseignant(en);
            Reclamation recEnregistrer = reclamationRepository.save(rec.fromDtoToEntity(rec));
            return (rec.fromEntityToDto(recEnregistrer));
        }
        else
            throw new InvalidEntityException( "La reclamation n'est pas valide !!!!", ErrorCodes.RECLAMATION_NOT_VALID,l);
    }

    @Override
    public ReclamationDto findById(int id) {
        Optional<Reclamation> recOptional = reclamationRepository.findById(id);
        ReclamationDto recdto = new ReclamationDto();
        Reclamation rec = recOptional.orElse(null);
        if  (rec != null) {
            ReclamationDto rec1 = new ReclamationDto();
            return rec1.fromEntityToDto(rec);
        }
        else
            throw new EntityNotFoundException("Reclamation not found !!!!!",ErrorCodes.RECLAMATION_NOT_FOUND);
    }

    @Override
    public List<ReclamationDto> findByDatecreation(Date d) {
        Optional <List <Reclamation>> l= reclamationRepository.findByDatecreation(d);
        List <ReclamationDto> l1 = new ArrayList<>();
        ReclamationDto rec = new ReclamationDto();
        List <Reclamation> lrec = l.orElse(null);
        if (lrec != null) {
            for (Reclamation i : lrec)
                l1.add(rec.fromEntityToDto(i));
            return l1;
        }
        else throw new EntityNotFoundException( "Reclamation not found !!!!" , ErrorCodes.RECLAMATION_NOT_FOUND);
    }

    @Override
    public List<ReclamationDto> findall() {
        List<ReclamationDto> l = new ArrayList<>();
        ReclamationDto rec = new ReclamationDto();
        for (Reclamation i : reclamationRepository.findAll())
            l.add (rec.fromEntityToDto(i));
        return l;
    }

    @Override
    public void delete(int id) {
        reclamationRepository.deleteById(id);
    }

    public ReclamationDto updateStatus (int id , status s,String to, String subject, String body){
        Optional <Reclamation> rec = reclamationRepository.findById(id);
        ReclamationDto recDto = new ReclamationDto();
        Reclamation r = rec.orElse(null);
        if (r != null ){
                if (r.getStatus() == status.en_attente && (s==status.résolu || s==status.en_cours)) {
                    r.setStatus(s);
                    // envoyer un email
                    mailsenderservice.sendNewMail(to,subject,body);
                    reclamationRepository.save(r);
                    return recDto.fromEntityToDto(r);
                }
                else if (r.getStatus() == status.en_cours && s==status.résolu ){
                    r.setStatus(s);
                    SimpleMailMessage message = new SimpleMailMessage();
                    // envoyer un email
                    mailsenderservice.sendNewMail(to,subject,body);
                    reclamationRepository.save(r);
                    return recDto.fromEntityToDto(r);
                }
                else
                    throw new InvalidEntityException("Status not valid !!!!!!",ErrorCodes.STAUS_NOT_VALID);
        }
        else throw new EntityNotFoundException("Reclamation not found !!!!!",ErrorCodes.RECLAMATION_NOT_FOUND);
    }

    public String getMostReclaimedClass (){
        Map<Integer , Integer > map = new HashMap<>();
        for (Reclamation en : reclamationRepository.findAll()){
            if (map.containsKey(en.getSalle()))
                map.put(en.getSalle(), map.get(en.getSalle()) + 1);
            else
                map.put (en.getSalle(),1);
        }
        int maxValue = 0,maxKey=0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxKey = entry.getKey();
                maxValue = entry.getValue();
            }
        }
        if (maxKey == 0)
            throw new EntityNotFoundException("Pas de salle",ErrorCodes.SALLE_NOT_FOUND);
        else
            return "la salle la plus reclamme est "+maxKey;
    }
    public void trierParDateCreation(List<Reclamation> listeReclamations) {
        Collections.sort(listeReclamations, new Comparator<Reclamation>() {
            @Override
            public int compare(Reclamation r1, Reclamation r2) {
                return r1.getDatecreation().compareTo(r2.getDatecreation());
            }
        });
    }
    public List<ReclamationDto> findSorted (){
        List <Reclamation> l = new ArrayList<>();
        List<Reclamation> l1 = new ArrayList<>();
        List<Reclamation> l2 = new ArrayList<>();
        List<Reclamation> l3 = new ArrayList<>();
        l = reclamationRepository.findAll();
        for (Reclamation r : l){
            if (r.getUrgence()== urgence.moyenne)
                l1.add(r);
            if (r.getUrgence()== urgence.critique)
                l2.add(r);
            else
                l3.add(r);
        }
        this.trierParDateCreation(l1);
        this.trierParDateCreation(l2);
        this.trierParDateCreation(l3);
        l2.addAll(l1);
        l2.addAll(l3);
        List <ReclamationDto> ldto = new ArrayList<>();
        ReclamationDto recdto = new ReclamationDto();
        for(Reclamation rec : l2){
            ldto.add(recdto.fromEntityToDto(rec));
        }
        return ldto;
    }



}
