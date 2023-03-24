package com.pragma.powerup.infrastructure.exceptionhandler;

import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.exception.UserMustBeOwnerException;
import com.pragma.powerup.infrastructure.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception exception) {
        System.out.println("llega Excepcion:"+exception.getClass().toString());
        String messageError = "";
        String messageException = "";
        switch (exception.getClass().toString()) {
            case "class com.pragma.powerup.infrastructure.exception.UserMustBeOwnerException":
                messageError = "Message Error";
                messageException = "id_propietario Must Be Owner";
                //messageException = exception.getClass().toString();
                break;

            case "class com.pragma.powerup.infrastructure.exception.UserNotExistException":
                messageError = "Message Error";
                messageException = "User Not Exist";
                //messageException = exception.getClass().toString();
                break;

            case "class com.pragma.powerup.infrastructure.exception.OwnerAuthMustBeOwnerRestuarant":
                messageError = "Message Error";
                messageException = "Authenticated owner must own the restaurant";
                //messageException = exception.getClass().toString();
                break;
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Collections.singletonMap(exception.getClass().toString(), exception.getMessage()));

        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap(messageError, messageException));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }
    
}