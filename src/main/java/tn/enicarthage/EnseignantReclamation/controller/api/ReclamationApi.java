package tn.enicarthage.EnseignantReclamation.controller.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EnseignantReclamation.Enum.status;
import tn.enicarthage.EnseignantReclamation.dto.ReclamationDto;
import tn.enicarthage.EnseignantReclamation.model.Reclamation;
import tn.enicarthage.EnseignantReclamation.model.UpdatePasswordRequest;
import tn.enicarthage.EnseignantReclamation.model.UpdateStatusRequest;

import java.util.Date;
import java.util.List;

public interface ReclamationApi {

    @PostMapping(value = "/reclamation" ,consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    ReclamationDto save (@RequestBody ReclamationDto rec);

    @GetMapping(value = "/reclamationbyid/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    ReclamationDto findById (@PathVariable int id);

    @GetMapping(value = "reclamationbydate/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ReclamationDto> findByDatecreation (@PathVariable Date date);

    @GetMapping(value = "/reclamation" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<ReclamationDto> findAll();

    @GetMapping(value = "/reclamationsorted" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<ReclamationDto> findSorted();

    @DeleteMapping("/reclamation/{id}")
    void delete (@PathVariable int id);

    @PatchMapping (value ="/reclamation/updatestatus/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    ReclamationDto updateStatus (@PathVariable int id , @RequestBody UpdateStatusRequest updaterequest);

    @GetMapping (value="/getMostReclaimedClass" , produces = MediaType.APPLICATION_JSON_VALUE)
    String getMostReclaimedClass ();
}
