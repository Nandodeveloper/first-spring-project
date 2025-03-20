package com.nando.screenmatch_spring.exception;

public class TitleNotFoundException extends RuntimeException {
    private String message;

    public TitleNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
