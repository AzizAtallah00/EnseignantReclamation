package tn.enicarthage.EnseignantReclamation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tn.enicarthage.EnseignantReclamation.Enum.status;
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class UpdateStatusRequest {
    private status status;
    private String to;
    private String subject;
    private String body;
}
