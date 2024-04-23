package tn.enicarthage.EnseignantReclamation.validator;

import org.springframework.util.StringUtils;
import tn.enicarthage.EnseignantReclamation.dto.ReclamationDto;

import java.util.ArrayList;
import java.util.List;

public class ReclamationValidaor {

    public List<String> validate (ReclamationDto rec){
        List<String> errors = new ArrayList<>();

        if (rec == null)
            errors.add("la reclamation est nulle ");
        else {
            if (!StringUtils.hasLength(rec.getEquipement()))
                errors.add("l'equipement est vide");
            if (rec.getUrgence() == null)
                errors.add("l'urgence est nulle");
        }
        return errors;
    }
}
