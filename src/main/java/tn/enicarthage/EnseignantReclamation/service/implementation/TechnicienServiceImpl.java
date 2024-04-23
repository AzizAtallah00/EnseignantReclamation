package tn.enicarthage.EnseignantReclamation.service.implementation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tn.enicarthage.EnseignantReclamation.Enum.specialite;
import tn.enicarthage.EnseignantReclamation.dto.ReclamationDto;
import tn.enicarthage.EnseignantReclamation.dto.TechnicienDto;
import tn.enicarthage.EnseignantReclamation.exception.EntityNotFoundException;
import tn.enicarthage.EnseignantReclamation.exception.ErrorCodes;
import tn.enicarthage.EnseignantReclamation.exception.InvalidEntityException;
import tn.enicarthage.EnseignantReclamation.model.Reclamation;
import tn.enicarthage.EnseignantReclamation.model.Technicien;
import tn.enicarthage.EnseignantReclamation.repository.TechnicienRepository;
import tn.enicarthage.EnseignantReclamation.service.TechnicienService;
import tn.enicarthage.EnseignantReclamation.validator.TechnicienValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class TechnicienServiceImpl implements TechnicienService {

    @Autowired
    private TechnicienRepository technicienRepository;

    @Override
    public TechnicienDto save(TechnicienDto tech) {
        TechnicienValidator techValid = new TechnicienValidator();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String passwordEncoded = encoder.encode (tech.getPassword());
        //assertTrue(encoder.matches("myPassword", result));
        tech.setPassword(passwordEncoded);
        List <String> errors = techValid.validate (tech);
        if (errors.isEmpty()) {
            Optional<Technicien> techopt = technicienRepository.findByMail(tech.getMail());
            Technicien techn = techopt.orElse(null);
            if (techn == null)
            {
                Technicien techEnregistrer = technicienRepository.save(tech.fromDtoToEntity(tech));
                return tech.fromEntityToDto(techEnregistrer);
            }
            else
                throw new InvalidEntityException("There is already an account registered with the same email",ErrorCodes.TECHNICIEN_NOT_VALID,errors);
        }
        else {
            throw new InvalidEntityException("Technicien is not valid", ErrorCodes.TECHNICIEN_NOT_VALID,errors);
        }

    }

    @Override
    public TechnicienDto findById(int id) {
        Optional<Technicien> techOptional = technicienRepository.findById(id);
        TechnicienDto techdto = new TechnicienDto();
        Technicien tech = techOptional.orElse(null);
        if  (tech != null) {
            TechnicienDto tech1 = new TechnicienDto();
            return tech1.fromEntityToDto(tech);
        }
        else
            throw new EntityNotFoundException("Technicien not found !!!!!",ErrorCodes.TECHNICIEN_NOT_FOUND);
    }

    @Override
    public List<TechnicienDto> findBySpecialite(specialite spec) {
        Optional<List<Technicien>> techOptional = technicienRepository.findBySpecialite(spec);
        TechnicienDto techdto = new TechnicienDto();
        List<Technicien> listtech = techOptional.orElse(null);
        List <TechnicienDto> l1 = new ArrayList<>();
        if  (listtech != null) {
            for (Technicien i : listtech)
                l1.add(techdto.fromEntityToDto(i));
            return l1;
        }
        else
            throw new EntityNotFoundException("Technicien not found !!!!!",ErrorCodes.TECHNICIEN_NOT_FOUND);
    }

    @Override
    public List<TechnicienDto> findByDisponible() {
        Optional<List<Technicien>> techOptional = technicienRepository.findByDisponible(true);
        TechnicienDto techdto = new TechnicienDto();
        List<Technicien> listtech = techOptional.orElse(null);
        List <TechnicienDto> l1 = new ArrayList<>();
        if  (listtech != null) {
            for (Technicien i : listtech)
                l1.add(techdto.fromEntityToDto(i));
            return l1;
        }
        else
            throw new EntityNotFoundException("Technicien not found !!!!!",ErrorCodes.TECHNICIEN_NOT_FOUND);
    }

    @Override
    public List<TechnicienDto> findall() {
        List<TechnicienDto> l = new ArrayList<>();
        TechnicienDto tech = new TechnicienDto();
        for (Technicien i : technicienRepository.findAll())
            l.add (tech.fromEntityToDto(i));
        return l;
    }


    @Override
    public void delete(int id) {
        technicienRepository.deleteById(id);
    }

    public int calculateEarnings(int id) {
        Optional<Technicien> techOptional = technicienRepository.findById(id);
        TechnicienDto techdto = new TechnicienDto();
        Technicien tech = techOptional.orElse(null);
        if  (tech != null) {
            return tech.getNbReclamation()*20;
        }
        else
            throw new EntityNotFoundException("Technicien not found !!!!!",ErrorCodes.TECHNICIEN_NOT_FOUND);
    }


}
