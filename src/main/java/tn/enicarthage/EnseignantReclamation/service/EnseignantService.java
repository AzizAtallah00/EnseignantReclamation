package tn.enicarthage.EnseignantReclamation.service;

import org.springframework.http.ResponseEntity;
import tn.enicarthage.EnseignantReclamation.dto.EnseignantDto;
import java.util.List;

public interface EnseignantService {
    EnseignantDto save (EnseignantDto ens);
    EnseignantDto findById (int id);
    EnseignantDto findByName (String name);
    EnseignantDto findByMail (String mail);
    List<EnseignantDto> findAll();
    void delete (int id);
    String SignIn (String mail , String password);
    String updatePassword (int id , String password , String newpass);

}
