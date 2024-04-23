package tn.enicarthage.EnseignantReclamation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EnseignantReclamation.Enum.specialite;
import tn.enicarthage.EnseignantReclamation.model.Technicien;

import java.util.List;
import java.util.Optional;

public interface TechnicienRepository extends JpaRepository<Technicien,Integer> {

    Optional <List<Technicien>> findBySpecialite (specialite specialite);

    Optional <List<Technicien>>  findByDisponible (Boolean v);

    Optional <Technicien> findByMail(String mail);

}
