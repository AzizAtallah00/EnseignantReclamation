package tn.enicarthage.EnseignantReclamation.handlers;

import lombok.*;
import tn.enicarthage.EnseignantReclamation.exception.ErrorCodes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ErrorDto {
    private Integer httpCode ;
    private ErrorCodes code;
    private String message;
    private List<String> errors = new ArrayList<>();
}
