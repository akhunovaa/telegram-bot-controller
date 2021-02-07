package com.botmasterzzz.controller.core;

public enum Language {

    RUSSIAN("ru"),
    ENGLISH("en"),
    CHINESE("cn"),
    SPAIN("es"),
    ru("ru"),
    en("en"),
    cn("cn"),
    es("es");

    private final String shortCode;

    Language(String code) {
        this.shortCode = code;
    }

    public String getShortCode() {
        return this.shortCode;
    }

}