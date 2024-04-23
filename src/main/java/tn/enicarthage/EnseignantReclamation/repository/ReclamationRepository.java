package tn.enicarthage.EnseignantReclamation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EnseignantReclamation.dto.ReclamationDto;
import tn.enicarthage.EnseignantReclamation.model.Reclamation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReclamationRepository extends JpaRepository<Reclamation , Integer> {

    Optional<List<Reclamation>> findByDatecreation (Date d);
}
