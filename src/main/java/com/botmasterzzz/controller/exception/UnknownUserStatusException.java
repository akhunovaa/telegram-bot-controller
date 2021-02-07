package com.botmasterzzz.controller.exception;

public class UnknownUserStatusException extends RuntimeException {

    public UnknownUserStatusException() {
        super("Unknown user status");
    }

    @Override
    public String getMessage() {
        return "Unknown user status";
    }
}
