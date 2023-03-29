package com.pragma.powerup.infrastructure.exceptionhandler;

import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    //@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidateExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fielName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();

            errors.put(fielName, message);
        });
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception exception) {
        System.out.println("llega Excepcion:"+exception.getClass().toString());
        String messageError = "";
        String messageException = "";
        switch (exception.getClass().toString()) {
            case "class com.pragma.powerup.domain.exception.UserMustBeOwnerException":
                messageError = "Message Error";
                messageException = "id_propietario Must Be Owner";
                //messageException = exception.getClass().toString();
                break;
            case "class com.pragma.powerup.domain.exception.UserNotExistException":
                messageError = "Message Error";
                messageException = "User Not Exist";
                break;
            case "class com.pragma.powerup.domain.exception.OwnerAuthMustBeOwnerRestaurantException":
                messageError = "Message Error";
                messageException = "Authenticated owner must own the restaurant";
                break;
            case "class com.pragma.powerup.domain.exception.DishNotExistException":
                messageError = "Message Error";
                messageException = "The dish not exist";
                break;
            case "class com.pragma.powerup.domain.exception.RestaurantNotExistException":
                messageError = "Message Error";
                messageException = "The restaurant not exist";
                break;
            case "class com.pragma.powerup.domain.exception.CategoryNotExistException":
                messageError = "Message Error";
                messageException = "The category not exist";
                break;
            case "class com.pragma.powerup.domain.exception.ClientHasAnOrderException":
                messageError = "Message Error";
                messageException = "The client has an order";
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