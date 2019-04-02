package br.com.fiap.orderservice.exception;

import static br.com.fiap.orderservice.exception.Exceptions.toMap;

public class ServerException extends Exception {
    public ServerException(String... searchParamsMap) {
        super("Internal server error " + toMap(String.class, String.class, searchParamsMap));
    }
}
