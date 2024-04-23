package tn.enicarthage.EnseignantReclamation.service;

import tn.enicarthage.EnseignantReclamation.Enum.status;
import tn.enicarthage.EnseignantReclamation.dto.ReclamationDto;

import java.util.Date;
import java.util.List;

public interface ReclamationService {

    ReclamationDto save (ReclamationDto rec , String token);

    ReclamationDto findById (int id);

    List <ReclamationDto> findByDatecreation (Date d);

    List<ReclamationDto> findall();

    List<ReclamationDto> findSorted();

    void delete (int id);

    ReclamationDto updateStatus (int id , status s,String to, String subject, String body);

    String getMostReclaimedClass ();
}
