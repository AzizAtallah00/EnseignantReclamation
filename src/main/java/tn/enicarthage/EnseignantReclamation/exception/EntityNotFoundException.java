package tn.enicarthage.EnseignantReclamation.exception;

import lombok.Getter;

public class EntityNotFoundException extends RuntimeException{
    @Getter
    private ErrorCodes errorcode;

    public EntityNotFoundException (String message){
        super (message);
    }

    public EntityNotFoundException (String message , Throwable cause){
        super(message , cause);
    }

    public EntityNotFoundException (String message , Throwable cause , ErrorCodes code){
        super (message, cause);
        this.errorcode = code ;
    }

    public EntityNotFoundException (String message , ErrorCodes code){
        super(message);
        this.errorcode = code;
    }

}
