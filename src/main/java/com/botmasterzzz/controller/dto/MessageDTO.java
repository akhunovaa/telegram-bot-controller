package com.botmasterzzz.controller.dto;

public class MessageDTO {

    private Long id;
    private String replyMarkup;
    private Integer messageId;
    private Long telegramUserId;
    private Long messageDate;
    private Long chatId;
    private String text;
    private String caption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReplyMarkup() {
        return replyMarkup;
    }

    public void setReplyMarkup(String replyMarkup) {
        this.replyMarkup = replyMarkup;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Long getTelegramUserId() {
        return telegramUserId;
    }

    public void setTelegramUserId(Long telegramUserId) {
        this.telegramUserId = telegramUserId;
    }

    public Long getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Long messageDate) {
        this.messageDate = messageDate;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id=" + id +
                ", replyMarkup='" + replyMarkup + '\'' +
                ", messageId=" + messageId +
                ", telegramUserId=" + telegramUserId +
                ", messageDate=" + messageDate +
                ", chatId=" + chatId +
                ", text='" + text + '\'' +
                ", caption='" + caption + '\'' +
                '}';
    }
}
