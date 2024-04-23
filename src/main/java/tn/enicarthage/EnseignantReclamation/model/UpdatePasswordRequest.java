package tn.enicarthage.EnseignantReclamation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest {

    private String password;
    private String newpass ;
}
