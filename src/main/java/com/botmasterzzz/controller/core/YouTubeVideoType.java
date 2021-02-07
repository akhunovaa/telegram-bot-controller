package com.botmasterzzz.controller.core;

public enum YouTubeVideoType {

    HEART("HEART"),
    LIKE("LIKE"),
    DISLIKE("DISLIKE");

    private final String code;

    YouTubeVideoType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
