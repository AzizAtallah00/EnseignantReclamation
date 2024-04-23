package tn.enicarthage.EnseignantReclamation.service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import tn.enicarthage.EnseignantReclamation.repository.TechnicienRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TechnicienServiceImplTest {
    @Mock
    private TechnicienRepository technicienRepository;
    @InjectMocks
    private TechnicienServiceImpl technicienService;

    private TechnicienRepository tech;
    @Test
    void getAllTechnicienTest() {
        var  personList = technicienService.findall();
        assertThat(personList).isNotNull();

    }
}