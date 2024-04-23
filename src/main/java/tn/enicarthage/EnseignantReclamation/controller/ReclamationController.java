package tn.enicarthage.EnseignantReclamation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.enicarthage.EnseignantReclamation.Enum.status;
import tn.enicarthage.EnseignantReclamation.controller.api.ReclamationApi;
import tn.enicarthage.EnseignantReclamation.dto.EnseignantDto;
import tn.enicarthage.EnseignantReclamation.dto.ReclamationDto;
import tn.enicarthage.EnseignantReclamation.model.UpdateStatusRequest;
import tn.enicarthage.EnseignantReclamation.service.implementation.ReclamationServiceImpl;

import java.util.Date;
import java.util.List;

//import static jdk.internal.org.jline.utils.Colors.s;

@RestController
public class ReclamationController implements ReclamationApi {

    @Autowired
    private ReclamationServiceImpl reclamationService;

    public ReclamationController (ReclamationServiceImpl rec){
        this.reclamationService = rec ;
    }

    @Override
    public ReclamationDto save(ReclamationDto rec) {
        String token = rec.getToken();
        return reclamationService.save(rec,token);
    }

    @Override
    public ReclamationDto findById(int id) {
        return reclamationService.findById(id);
    }

    @Override
    public List<ReclamationDto> findByDatecreation(Date date) {
        return reclamationService.findByDatecreation(date);
    }

    @Override
    public List<ReclamationDto> findAll() {
        return reclamationService.findall();
    }

    public List<ReclamationDto> findSorted(){return reclamationService.findSorted();}

    @Override
    public void delete(int id) {
        reclamationService.delete(id);
    }

    public ReclamationDto updateStatus (int id,UpdateStatusRequest updatereq){
        status s = updatereq.getStatus();
        String to=updatereq.getTo();
        String subject =updatereq.getSubject();
        String body = updatereq.getBody();

        return reclamationService.updateStatus(id,s,to,subject,body);
    }

    @Override
    public String getMostReclaimedClass() {
        return reclamationService.getMostReclaimedClass();
    }
}
