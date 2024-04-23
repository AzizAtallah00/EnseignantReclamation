package tn.enicarthage.EnseignantReclamation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.enicarthage.EnseignantReclamation.controller.api.EnseignantApi;
import tn.enicarthage.EnseignantReclamation.dto.EnseignantDto;
import tn.enicarthage.EnseignantReclamation.model.UpdatePasswordRequest;
import tn.enicarthage.EnseignantReclamation.model.signInRequest;
import tn.enicarthage.EnseignantReclamation.service.EnseignantService;

import java.util.List;

@RestController
public class EnseignantController implements EnseignantApi {
    @Autowired //permet d'injecter le service dans le controller
    private EnseignantService enseignantService;


    public EnseignantController(EnseignantService ens){
        this.enseignantService = ens;
    }
    @Override
    public EnseignantDto save(EnseignantDto ens) {
        return enseignantService.save(ens);
    }

    @Override
    public EnseignantDto findById(int id) {
        return enseignantService.findById(id);
    }

    @Override
    public EnseignantDto findByName(String name) {
        return enseignantService.findByName(name);
    }

    @Override
    public EnseignantDto findByMail (String mail){
        return enseignantService.findByMail(mail);
    }

    @Override
    public List<EnseignantDto> findAll() {
        return enseignantService.findAll();
    }

    @Override
    public void delete(int id) {
        enseignantService.delete(id);
    }

    public String signIn (signInRequest signInRequest){
        String mail = signInRequest.getMail();
        String password = signInRequest.getPassword();
        return enseignantService.SignIn(mail , password);
    }

    public String updatePassword (int id , UpdatePasswordRequest updaterequest){
        return enseignantService.updatePassword(id  , updaterequest.getPassword() , updaterequest.getNewpass());
    }
}
