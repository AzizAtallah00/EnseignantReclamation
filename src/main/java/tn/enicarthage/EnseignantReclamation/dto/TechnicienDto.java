package tn.enicarthage.EnseignantReclamation.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.enicarthage.EnseignantReclamation.Enum.specialite;
import tn.enicarthage.EnseignantReclamation.model.Reclamation;
import tn.enicarthage.EnseignantReclamation.model.Technicien;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TechnicienDto {
    private int id;
    private String name;
    private String mail;
    private String password ;
    private int telephone;
    @Enumerated(EnumType.STRING)
    private specialite specialite ;
    private Boolean disponible;
    private List<Reclamation> reclamations ;
    private int nbReclamation;


    public TechnicienDto fromEntityToDto (Technicien tech){
        TechnicienDto techdto = new TechnicienDto();
        if (tech == null)
            return null;
        else {
            return techdto.builder().
                    id(tech.getId()).
                    name(tech.getName()).
                    mail(tech.getMail()).
                    password(tech.getPassword()).
                    telephone(tech.getTelephone()).
                    specialite(tech.getSpecialite()).
                    disponible(tech.getDisponible()).
                    nbReclamation(tech.getNbReclamation()).
                    build();
        }
    }

    public Technicien fromDtoToEntity (TechnicienDto techdto){
        Technicien tech = new Technicien();
        if (tech == null)
            return null;
        else {
            tech.setId(techdto.getId());
            tech.setName(techdto.getName());
            tech.setMail(techdto.getMail());
            tech.setPassword(techdto.getPassword());
            tech.setTelephone(techdto.getTelephone());
            tech.setSpecialite(techdto.getSpecialite());
            tech.setDisponible(techdto.getDisponible());
            tech.setNbReclamation(techdto.getNbReclamation());
            return tech;
        }
    }

}
