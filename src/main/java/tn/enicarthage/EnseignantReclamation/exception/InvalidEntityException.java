package tn.enicarthage.EnseignantReclamation.exception;
import lombok.Getter;

import  java.util.List;
public class InvalidEntityException extends RuntimeException{

    @Getter
    private ErrorCodes errorcode ;
    @Getter
    private List<String> errors;

    public InvalidEntityException (String message){
        super (message);
    }

    public InvalidEntityException (String message , Throwable cause){
        super(message , cause);
    }

    public InvalidEntityException (String message , Throwable cause , ErrorCodes code){
        super (message, cause);
        this.errorcode = code ;
    }

    public InvalidEntityException (String message , ErrorCodes code){
        super(message);
        this.errorcode = code;
    }

    public InvalidEntityException (String message ,ErrorCodes c , List<String> l){
        super(message);
        this.errorcode = c;
        this.errors = l;
    }


}
