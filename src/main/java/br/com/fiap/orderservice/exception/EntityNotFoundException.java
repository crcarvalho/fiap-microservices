package br.com.fiap.orderservice.exception;


import static br.com.fiap.orderservice.exception.Exceptions.generateMessage;
import static br.com.fiap.orderservice.exception.Exceptions.toMap;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(Class clazz, String... searchParamsMap) {
        super(generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }
}