package com.botmasterzzz.controller.core;

public enum TouchType {

    HEART("HEART"),
    LIKE("LIKE"),
    DISLIKE("DISLIKE");

    private final String code;

    TouchType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}