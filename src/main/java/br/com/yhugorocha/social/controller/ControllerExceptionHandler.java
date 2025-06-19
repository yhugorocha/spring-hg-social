package br.com.yhugorocha.social.controller;

import br.com.yhugorocha.social.exception.BusinessException;
import br.com.yhugorocha.social.exception.PostNotFoundException;
import br.com.yhugorocha.social.exception.StandartError;
import br.com.yhugorocha.social.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<StandartError> userNotFoundHandler(UserNotFoundException e){
        StandartError error = new StandartError(HttpStatus.NOT_FOUND.value(), e.getMessage(),
                e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(PostNotFoundException.class)
    private ResponseEntity<StandartError> postNotFoundHandler(PostNotFoundException e){
        StandartError error = new StandartError(HttpStatus.NOT_FOUND.value(), e.getMessage(),
                e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<StandartError> businessRuleHandler(BusinessException e){
        StandartError error = new StandartError(HttpStatus.CONFLICT.value(), e.getMessage(),
                e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
