package tn.enicarthage.EnseignantReclamation.controller.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EnseignantReclamation.dto.EnseignantDto;
import tn.enicarthage.EnseignantReclamation.model.UpdatePasswordRequest;
import tn.enicarthage.EnseignantReclamation.model.signInRequest;

import java.awt.*;
import java.util.List;

public interface EnseignantApi {

    @PostMapping(value = "/enseignant", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        //on va rcevoir un objet json et retourner un objet json
    EnseignantDto save(@RequestBody EnseignantDto ens);

    @GetMapping(value = "/enseignants/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    EnseignantDto findById ( @PathVariable int id);

    @GetMapping(value = "/enseignante/{name}" , produces = MediaType.APPLICATION_JSON_VALUE)
    EnseignantDto findByName (@PathVariable  String name);

    @GetMapping(value = "/enseignantbymail/{mail}" , produces = MediaType.APPLICATION_JSON_VALUE)
    EnseignantDto findByMail (@PathVariable  String mail);

    @GetMapping(value = "/enseignant" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<EnseignantDto> findAll();
    @DeleteMapping(value = "/enseignant/{id}" )
    void delete ( @PathVariable int id);

    @PostMapping(value = "/enseignant/signin" ,produces = MediaType.APPLICATION_JSON_VALUE )
    String signIn (@RequestBody signInRequest signInRequest );

    @PatchMapping(value ="/updatepassword/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    String updatePassword (@PathVariable int id  , @RequestBody UpdatePasswordRequest updaterequest);
}
