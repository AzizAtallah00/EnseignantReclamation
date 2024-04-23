package tn.enicarthage.EnseignantReclamation.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.enicarthage.EnseignantReclamation.model.Enseignant;
import tn.enicarthage.EnseignantReclamation.model.Reclamation;
import tn.enicarthage.EnseignantReclamation.Enum.status;
import tn.enicarthage.EnseignantReclamation.Enum.urgence;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ReclamationDto {
    private Integer id ;
    private Date datecreation ;
    private String equipement;
    @Enumerated(EnumType.STRING)
    private urgence urgence;
    private String description;
    @Enumerated(EnumType.STRING)
    private status status;
    private int salle;
    private Enseignant enseignant;
    private String token;


    public  ReclamationDto fromEntityToDto (Reclamation r)
    {
        if (r == null)
            //gerer les erreur
            return null;
        else
            return ReclamationDto.builder().
                    id(r.getId()).
                    equipement(r.getEquipement()).
                    urgence(r.getUrgence()).
                    datecreation(r.getDatecreation()).
                    status(r.getStatus()).
                    description(r.getDescription()).
                    salle(r.getSalle()).
                    enseignant(r.getEnseignant())
                    .build();
    }

    public  Reclamation fromDtoToEntity (ReclamationDto rec)
    {
        if (rec == null )
            return null ;
        else {
            Reclamation reclamation = new Reclamation ();
            reclamation.setDatecreation(rec.getDatecreation());
            reclamation.setEquipement(rec.getEquipement());
            reclamation.setUrgence(rec.getUrgence());
            reclamation.setStatus(rec.getStatus());
            reclamation.setId(rec.getId());
            reclamation.setDescription(rec.getDescription());
            reclamation.setSalle(rec.getSalle());
            reclamation.setEnseignant(rec.getEnseignant());
            return reclamation;
        }
    }


}

