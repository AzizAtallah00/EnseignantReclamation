package tn.enicarthage.EnseignantReclamation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.enicarthage.EnseignantReclamation.Enum.status;
import tn.enicarthage.EnseignantReclamation.Enum.urgence;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity


public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String equipement;
    @Enumerated(EnumType.STRING)
    private urgence urgence;
    private Date datecreation ;
    private String description;
    @Enumerated(EnumType.STRING)
    private status status;
    private int salle ;
    @ManyToOne
    @JoinColumn(name = "idenseignant")
    private Enseignant enseignant;
    @ManyToOne
    @JoinColumn(name = "idtechnicien")
    private Technicien technicien;

}

