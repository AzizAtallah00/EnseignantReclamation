package tn.enicarthage.EnseignantReclamation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EnseignantReclamation.dto.EnseignantDto;
import tn.enicarthage.EnseignantReclamation.model.Enseignant;

import java.util.List;
import java.util.Optional;

public interface EnseignantRepository extends JpaRepository< Enseignant , Integer> {

    Optional<Enseignant> findByName (String name);

    Optional<Enseignant> findByMail (String mail);

}
