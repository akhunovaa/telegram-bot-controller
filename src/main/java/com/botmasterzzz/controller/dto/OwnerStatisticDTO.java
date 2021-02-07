package com.botmasterzzz.controller.dto;

public class OwnerStatisticDTO {

    private long countOfTouch;
    private String touchType;

    public long getCountOfTouch() {
        return countOfTouch;
    }

    public void setCountOfTouch(long countOfTouch) {
        this.countOfTouch = countOfTouch;
    }

    public String getTouchType() {
        return touchType;
    }

    public void setTouchType(String touchType) {
        this.touchType = touchType;
    }

    @Override
    public String toString() {
        return "OwnerStatisticDTO{" +
                "countOfTouch=" + countOfTouch +
                ", touchType='" + touchType + '\'' +
                '}';
    }
}
