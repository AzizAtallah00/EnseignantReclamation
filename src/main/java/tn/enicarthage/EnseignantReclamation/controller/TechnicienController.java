package tn.enicarthage.EnseignantReclamation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.enicarthage.EnseignantReclamation.Enum.specialite;
import tn.enicarthage.EnseignantReclamation.controller.api.TechnicienApi;
import tn.enicarthage.EnseignantReclamation.dto.TechnicienDto;
import tn.enicarthage.EnseignantReclamation.service.TechnicienService;

import java.util.List;

@RestController
public class TechnicienController implements TechnicienApi {
    @Autowired
    private TechnicienService technicienService ;

    @Override
    public TechnicienDto save(TechnicienDto tech) {
        return technicienService.save(tech);
    }

    @Override
    public TechnicienDto findById(int id) {
        return technicienService.findById(id);
    }

    @Override
    public List<TechnicienDto> findBySpecialite(specialite spec) {
        return technicienService.findBySpecialite(spec);
    }

    @Override
    public List<TechnicienDto> findByDisponible() {
        return technicienService.findByDisponible();
    }

    @Override
    public List<TechnicienDto> findAll() {
        return technicienService.findall();
    }

    @Override
    public void delete(int id) {
        technicienService.delete(id);

    }
}
