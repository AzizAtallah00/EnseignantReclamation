package tn.enicarthage.EnseignantReclamation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.enicarthage.EnseignantReclamation.Enum.specialite;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Technicien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String mail;
    private String password ;
    private int telephone;
    @Enumerated(EnumType.STRING)
    private specialite specialite ;
    private Boolean disponible ;
    @OneToMany(mappedBy = "technicien")
    private List<Reclamation> reclamations ;
    private int nbReclamation;

}
