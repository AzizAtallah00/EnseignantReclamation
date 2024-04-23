package tn.enicarthage.EnseignantReclamation.service.implementation;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EnseignantReclamation.dto.EnseignantDto;
import tn.enicarthage.EnseignantReclamation.exception.EntityNotFoundException;
import tn.enicarthage.EnseignantReclamation.exception.ErrorCodes;
import tn.enicarthage.EnseignantReclamation.exception.InvalidEntityException;
import tn.enicarthage.EnseignantReclamation.repository.EnseignantRepository;
import tn.enicarthage.EnseignantReclamation.service.EnseignantService;
import tn.enicarthage.EnseignantReclamation.model.Enseignant;
import tn.enicarthage.EnseignantReclamation.validator.EnseignantValidator;


import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Builder
@NoArgsConstructor
@Service
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EnseignantServiceImpl implements EnseignantService {


    @Autowired
    private EnseignantRepository enseignantRepository;
    public static final String SECRET ="5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    @Autowired
    public EnseignantServiceImpl ( EnseignantRepository  ensr){
        this.enseignantRepository = ensr;
    }
    @Override
    public EnseignantDto save(EnseignantDto ens) {
        EnseignantValidator ensValid = new EnseignantValidator();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String passwordEncoded = encoder.encode (ens.getPassword());
        //assertTrue(encoder.matches("myPassword", result));
        ens.setPassword(passwordEncoded);
        List <String> errors = ensValid.validate (ens);
        if (errors.isEmpty()) {
            Optional<Enseignant> ensopt = enseignantRepository.findByMail(ens.getMail());
            Enseignant en = ensopt.orElse(null);
            if (en == null)
            {
            Enseignant enseignantEnregistrer = enseignantRepository.save(ens.fromDtoToEntity(ens));
            return ens.fromEnseignantToDto(enseignantEnregistrer);
            }
            else
                throw new InvalidEntityException("There is already an account registered with the same email",ErrorCodes.ENSEIGNANT_NOT_VALID,errors);
        }
        else {
            throw new InvalidEntityException("Enseignant is not valid", ErrorCodes.ENSEIGNANT_NOT_VALID,errors);
        }
    }

    @Override
    public EnseignantDto findById(int id) {
        Optional<Enseignant> optionalEns = enseignantRepository.findById(id);
        //traiter l'exception si l'article n'a pas été trouvé
        EnseignantDto ens1 = new EnseignantDto();
        /*if (optionalEns.isPresent()) {
            return (ens1.fromEnseignantToDto(optionalEns.get()));
        }
        else
            return (null);*/
        Enseignant en = optionalEns.orElse(null);
        if (en != null)//if optionalEns is null  en = null else en = object cherché
            return (ens1.fromEnseignantToDto(en));
        else
            throw new EntityNotFoundException("enseignant not found !!!" , ErrorCodes.ENSEIGNANT_NOT_FOUND);
    }

    @Override
    // findByName n'est pas predefinie donc je vais la definir dans l'interface EnseignantRepository puis l'implementer
    public EnseignantDto findByName(String name){
        Optional <Enseignant> optionalEns = enseignantRepository.findByName(name);
        EnseignantDto ens1 = new EnseignantDto();
        Enseignant l = new Enseignant();
        l = optionalEns.orElse(null);
        if (l != null){
            EnseignantDto l1 = new EnseignantDto();
                return l1.fromEnseignantToDto(l);
        }
        else
            throw new EntityNotFoundException("enseignant not found !!!", ErrorCodes.ENSEIGNANT_NOT_FOUND);
    }

    @Override
    public EnseignantDto findByMail (String mail){
        Optional<Enseignant> optionalEns = enseignantRepository.findByMail(mail);
        EnseignantDto ensDto = new EnseignantDto();
        Enseignant en = optionalEns.orElse(null);
        if (en != null)
            return (ensDto.fromEnseignantToDto(en));
        else
            throw new EntityNotFoundException("Enseignant not found !! ", ErrorCodes.ENSEIGNANT_NOT_FOUND);
    }

    @Override
    public List<EnseignantDto> findAll() {
         /*return enseignantRepository.findAll().stream()
                 .map(EnseignantDto::fromEnseignantToDto)
                 .collect(Collectors.toList());*/
        List <EnseignantDto> l = new ArrayList<>();
        EnseignantDto ensDto = new EnseignantDto();
        for (Enseignant en : enseignantRepository.findAll())
        {
            l.add(ensDto.fromEnseignantToDto(en));
        }
        return l;
    }

    @Override
    public void delete(int id) {
        Enseignant ens = new Enseignant();
        if (findById(id) != null)
            enseignantRepository.deleteById(id);
        else
            throw new EntityNotFoundException("No Enseignant with this id",ErrorCodes.ENSEIGNANT_NOT_FOUND);
    }

    @Override
    public String SignIn (String mail , String password){
        Optional<Enseignant> optionalEns = enseignantRepository.findByMail(mail);
        Enseignant en = optionalEns.orElse(null);
        if (en == null ) {
            return "Invalid Mail !!!!";
        }
        else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
            if(encoder.matches(password, en.getPassword())){
                String jwt = Jwts.builder()
                        .setSubject(String.valueOf(en.getId()))
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                        .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
                return jwt;
            }
            else {
                return "Invalid Password !!!!!!";
            }
        }
    }

    private SecretKey getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String updatePassword (int id  , String password , String newpass){
        Optional<Enseignant> optionalens = enseignantRepository.findById(id);
        Enseignant ens = optionalens.orElse(null);
        if( ens == null)
            throw new EntityNotFoundException("No Enseignant with this id !!!!", ErrorCodes.ENSEIGNANT_NOT_FOUND);
        else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
            if(encoder.matches(password, ens.getPassword() )){
                String passwordEncoded = encoder.encode (newpass);
                ens.setPassword(passwordEncoded);
                enseignantRepository.save(ens);
                return ("Password updated successfully !!!!");
            }
            else
                throw new InvalidEntityException("wrong password !!!!",ErrorCodes.PASSWORD_NOT_VALID);
        }
    }



}
