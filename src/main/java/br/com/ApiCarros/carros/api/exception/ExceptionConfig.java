package br.com.ApiCarros.carros.api.exception;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;
import org.springframework.security.access.AccessDeniedException;




@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            EmptyResultDataAccessException.class,
    })
    public ResponseEntity errorNotFound(Exception ex){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({
            IllegalArgumentException.class
    })
    public ResponseEntity badRequest(Exception ex){
        return ResponseEntity.badRequest().build();
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        return new ResponseEntity<>(new Error("Operação Não permitida!"), HttpStatus.METHOD_NOT_ALLOWED);
    }


    @ExceptionHandler({
            AccessDeniedException.class
    })
    public ResponseEntity accessDenied(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error("Acesso Negado!"));
    }


    class Error implements Serializable{
        private String error;
        public Error(String error){
            this.error=error;
        }
        public String getError() {
            return error;
        }
    }
}
