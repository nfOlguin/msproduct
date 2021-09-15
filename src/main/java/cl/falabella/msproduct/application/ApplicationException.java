package cl.falabella.msproduct.application;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ApplicationException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 781714802069329570L;
    private final HttpStatus httpStatus;


    public ApplicationException(String errorMessage, HttpStatus httpStatus) {
        super(errorMessage);
        this.httpStatus = httpStatus;
    }
}
