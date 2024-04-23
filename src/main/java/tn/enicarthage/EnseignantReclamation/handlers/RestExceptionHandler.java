package tn.enicarthage.EnseignantReclamation.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tn.enicarthage.EnseignantReclamation.exception.EntityNotFoundException;
import tn.enicarthage.EnseignantReclamation.exception.InvalidEntityException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class) // maanetha bech tssir ki talka EntityNotFoundException
    public ErrorDto handleException (EntityNotFoundException exception , WebRequest webRequest){

        final ErrorDto error = ErrorDto.builder()
                .code(exception.getErrorcode())
                .httpCode(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .build();

        return error;
        //return new ResponseEntity<> (error, HttpStatus.NOT_FOUND);
        //return ResponseEntity.notFound().body(exception.getMessage());
    }

    @ExceptionHandler(InvalidEntityException.class) // maanetha bech tssir ki talka InvalidEntityException
    public ErrorDto handleException (InvalidEntityException exception , WebRequest webRequest){

        final ErrorDto error = ErrorDto.builder()
                .code(exception.getErrorcode())
                .httpCode(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();

        return error;
    }

}
