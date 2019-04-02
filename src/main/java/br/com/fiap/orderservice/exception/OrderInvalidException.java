package br.com.fiap.orderservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class OrderInvalidException extends RuntimeException {

    public OrderInvalidException(String message){
        super(message);
    }
}
