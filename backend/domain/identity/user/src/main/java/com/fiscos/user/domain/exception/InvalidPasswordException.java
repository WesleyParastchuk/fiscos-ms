package com.fiscos.user.domain.exception;

public class InvalidPasswordException extends DomainException {
    public InvalidPasswordException(String reason) {
        super("Invalid password: " + reason);
    }
}