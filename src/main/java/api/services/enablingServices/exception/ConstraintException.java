package api.services.enablingServices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.hibernate.exception.ConstraintViolationException;

@ControllerAdvice
public class ConstraintException {
    @ExceptionHandler(value = { ConstraintViolationException.class })
    public ResponseEntity<Object> handleAnyException(ConstraintViolationException ex, WebRequest request) {
        return new ResponseEntity<>("Your request does not match the database constraints.",
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}