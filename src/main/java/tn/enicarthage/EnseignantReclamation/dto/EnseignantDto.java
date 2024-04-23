package tn.enicarthage.EnseignantReclamation.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.enicarthage.EnseignantReclamation.model.Enseignant;
import tn.enicarthage.EnseignantReclamation.model.Reclamation;
import tn.enicarthage.EnseignantReclamation.Enum.roles;


import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class EnseignantDto {
    private int id;
    private String name;
    private String mail;
    private String password ;
    @Enumerated(EnumType.STRING)
    private roles role;
    private List<Reclamation> reclamations ;


    public EnseignantDto fromEnseignantToDto (Enseignant ens)
    {
        if (ens == null)
            return null;
        else
            return EnseignantDto.builder().
                    id(ens.getId()).
                    name(ens.getName()).
                    mail(ens.getMail()).
                    password(ens.getPassword()).
                    role(ens.getRole()).
                    build();
    }
    public Enseignant fromDtoToEntity (EnseignantDto en)
    {
        if (en == null)
            return null;
        else{
            Enseignant ens = new Enseignant ();
            ens.setId(en.getId());
            ens.setName(en.getName());
            ens.setMail(en.getMail());
            ens.setPassword(en.getPassword());
            ens.setRole(en.getRole());
            return ens;
        }

    }
}




