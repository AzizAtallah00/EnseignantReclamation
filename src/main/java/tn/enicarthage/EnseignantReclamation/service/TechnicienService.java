package tn.enicarthage.EnseignantReclamation.service;

import tn.enicarthage.EnseignantReclamation.Enum.specialite;
import tn.enicarthage.EnseignantReclamation.Enum.status;
import tn.enicarthage.EnseignantReclamation.dto.ReclamationDto;
import tn.enicarthage.EnseignantReclamation.dto.TechnicienDto;

import java.util.Date;
import java.util.List;

public interface TechnicienService {
    TechnicienDto save (TechnicienDto tech);

    TechnicienDto findById (int id);

    List<TechnicienDto> findBySpecialite (specialite spec);
    List<TechnicienDto> findByDisponible();

    List<TechnicienDto> findall();

    void delete (int id);

    int calculateEarnings (int id);

}
