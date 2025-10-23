package com.fiscos.user.domain.exception;

public class EmailAlreadyRegisteredException extends DomainException {
    public EmailAlreadyRegisteredException(String email) {
        super("Email already registered: " + email);
    }
}