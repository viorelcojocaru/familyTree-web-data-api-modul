package com.esempla.familyTree.familyTreeapi.controller.jws.exceptions;

public class JWSException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;

    public JWSException(String message) {
        super(message);
        this.message = message;
    }
}
