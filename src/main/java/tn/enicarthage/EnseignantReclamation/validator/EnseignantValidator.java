package tn.enicarthage.EnseignantReclamation.validator;

import org.springframework.util.StringUtils;
import tn.enicarthage.EnseignantReclamation.dto.EnseignantDto;

import java.util.ArrayList;
import java.util.List;


public class EnseignantValidator {
    public List<String> validate (EnseignantDto ens){
        List<String> errors = new ArrayList<>();
        if (ens == null)
            errors.add ("l'enseignant est nulle");
        else {
            if (!StringUtils.hasLength(ens.getName()))
                errors.add("le name est vide");
            if (!StringUtils.hasLength(ens.getMail()))
                errors.add("le mail est vide");
            if (ens.getRole() == null)
                errors.add("le role est vide");
        }
         return errors;
    };
}
