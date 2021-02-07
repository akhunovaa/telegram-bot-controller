package com.botmasterzzz.controller.dto;

public class TelegramMessageDTO {

    private long id;
    private long chatId;
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TelegramMessageDTO{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", text='" + text + '\'' +
                '}';
    }
}
