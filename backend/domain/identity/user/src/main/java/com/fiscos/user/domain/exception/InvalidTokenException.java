package com.fiscos.user.domain.exception;

public class InvalidTokenException extends DomainException {
    public InvalidTokenException(String reason) {
        super("Invalid token: " + reason);
    }
}