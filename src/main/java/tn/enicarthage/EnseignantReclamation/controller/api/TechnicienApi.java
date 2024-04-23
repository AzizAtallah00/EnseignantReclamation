package tn.enicarthage.EnseignantReclamation.controller.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EnseignantReclamation.Enum.specialite;
import tn.enicarthage.EnseignantReclamation.dto.ReclamationDto;
import tn.enicarthage.EnseignantReclamation.dto.TechnicienDto;
import tn.enicarthage.EnseignantReclamation.model.UpdateStatusRequest;

import java.util.Date;
import java.util.List;

public interface TechnicienApi {
    @PostMapping(value = "/technicien" ,consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    TechnicienDto save (@RequestBody TechnicienDto tech);

    @GetMapping(value = "/technicienbyid/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    TechnicienDto findById (@PathVariable int id);

    @GetMapping(value = "technicienbyspecialite/{spec}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<TechnicienDto> findBySpecialite (@PathVariable specialite spec);

    @GetMapping(value = "technicienbydisponible", produces = MediaType.APPLICATION_JSON_VALUE)
    List<TechnicienDto> findByDisponible ();

    @GetMapping(value = "/technicien" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<TechnicienDto> findAll();



    @DeleteMapping("/technicien/{id}")
    void delete (@PathVariable int id);

}
