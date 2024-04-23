package tn.enicarthage.EnseignantReclamation.validator;

import org.springframework.util.StringUtils;
import tn.enicarthage.EnseignantReclamation.Enum.specialite;
import tn.enicarthage.EnseignantReclamation.dto.TechnicienDto;

import java.util.ArrayList;
import java.util.List;

public class TechnicienValidator {

    public List<String> validate (TechnicienDto tech){
        List<String> errors = new ArrayList<>();

        if (tech == null)
            errors.add("la reclamation est nulle ");
        else {
            if (!StringUtils.hasLength(tech.getMail()))
                errors.add("le mail est vide");
            if (!StringUtils.hasLength(tech.getName()))
                errors.add("le nom est vide");
            if (!StringUtils.hasLength(tech.getPassword()))
                errors.add ("le password est vide");
            if (tech.getTelephone()<11111111 || tech.getTelephone()>99999999)
                errors.add ("le telephone n'est pas valide");
            if (! (tech.getSpecialite() instanceof specialite))
                errors.add("la specialite n'est pas valide");
        }
        return errors;
    }
}
