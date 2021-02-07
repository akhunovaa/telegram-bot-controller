package com.botmasterzzz.controller.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bot_usage_statistics")
public class TelegramBotUsageStatisticEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "callback_data")
    private String callBackData;

    @Column(name = "message_id")
    private Integer messageId;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "messenger_id")
    private int messengerId;

    @JoinColumn(name = "bot_instance_id")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private TelegramInstanceEntity telegramInstanceEntity;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private TelegramBotUserEntity telegramBotUserEntity;

    @Column(name = "note")
    private String note;

    @JsonIgnore
    @Column(name = "aud_when_create")
    private Timestamp audWhenCreate;

    @JsonIgnore
    @Column(name = "aud_when_update")
    private Timestamp audWhenUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessengerId() {
        return messengerId;
    }

    public void setMessengerId(int messengerId) {
        this.messengerId = messengerId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public TelegramInstanceEntity getTelegramInstanceEntity() {
        return telegramInstanceEntity;
    }

    public void setTelegramInstanceEntity(TelegramInstanceEntity telegramInstanceEntity) {
        this.telegramInstanceEntity = telegramInstanceEntity;
    }

    public TelegramBotUserEntity getTelegramBotUserEntity() {
        return telegramBotUserEntity;
    }

    public void setTelegramBotUserEntity(TelegramBotUserEntity telegramBotUserEntity) {
        this.telegramBotUserEntity = telegramBotUserEntity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getAudWhenCreate() {
        return audWhenCreate;
    }

    public void setAudWhenCreate(Timestamp audWhenCreate) {
        this.audWhenCreate = audWhenCreate;
    }

    public Timestamp getAudWhenUpdate() {
        return audWhenUpdate;
    }

    public void setAudWhenUpdate(Timestamp audWhenUpdate) {
        this.audWhenUpdate = audWhenUpdate;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getCallBackData() {
        return callBackData;
    }

    public void setCallBackData(String callBackData) {
        this.callBackData = callBackData;
    }

    @Override
    public String toString() {
        return "TelegramBotUsageStatisticEntity{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", callBackData='" + callBackData + '\'' +
                ", messageId=" + messageId +
                ", chatId=" + chatId +
                ", messengerId=" + messengerId +
                ", telegramInstanceEntity=" + telegramInstanceEntity +
                ", telegramBotUserEntity=" + telegramBotUserEntity +
                ", note='" + note + '\'' +
                ", audWhenCreate=" + audWhenCreate +
                ", audWhenUpdate=" + audWhenUpdate +
                '}';
    }
}
