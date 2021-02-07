package com.botmasterzzz.controller.dto;

public class TopRatingUsersDTO {

    private long countOfTouch;
    private long userId;
    private long telegramUserId;
    private String telegramUserName;
    private String telegramFirstName;
    private String telegramLastName;

    public long getCountOfTouch() {
        return countOfTouch;
    }

    public void setCountOfTouch(long countOfTouch) {
        this.countOfTouch = countOfTouch;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTelegramUserId() {
        return telegramUserId;
    }

    public void setTelegramUserId(long telegramUserId) {
        this.telegramUserId = telegramUserId;
    }

    public String getTelegramUserName() {
        return telegramUserName;
    }

    public void setTelegramUserName(String telegramUserName) {
        this.telegramUserName = telegramUserName;
    }

    public String getTelegramFirstName() {
        return telegramFirstName;
    }

    public void setTelegramFirstName(String telegramFirstName) {
        this.telegramFirstName = telegramFirstName;
    }

    public String getTelegramLastName() {
        return telegramLastName;
    }

    public void setTelegramLastName(String telegramLastName) {
        this.telegramLastName = telegramLastName;
    }

    @Override
    public String toString() {
        return "TopRatingUsersDTO{" +
                "countOfTouch=" + countOfTouch +
                ", userId=" + userId +
                ", telegramUserId=" + telegramUserId +
                ", telegramUserName='" + telegramUserName + '\'' +
                ", telegramFirstName='" + telegramFirstName + '\'' +
                ", telegramLastName='" + telegramLastName + '\'' +
                '}';
    }
}
