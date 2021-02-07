package com.botmasterzzz.controller.entity;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "telegram_messages")
public class TelegramMessagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "reply_markup")
    private String replyMarkup;

    @Column(name = "message_id")
    private Integer messageId;

    @JoinColumn(name = "from_user")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private TelegramBotUserEntity telegramBotUserEntity;

    @Column(name = "message_date")
    private Timestamp messageDate;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "text")
    private String text;

    @Column(name = "caption")
    private String caption;

    @Column(name = "aud_when_create")
    private Timestamp audWhenCreate;

    @Column(name = "aud_when_update")
    private Timestamp audWhenUpdate;

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

    public TelegramBotUserEntity getTelegramBotUserEntity() {
        return telegramBotUserEntity;
    }

    public void setTelegramBotUserEntity(TelegramBotUserEntity telegramBotUserEntity) {
        this.telegramBotUserEntity = telegramBotUserEntity;
    }

    public Timestamp getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Timestamp messageDate) {
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

    @Override
    public String toString() {
        return "TelegramMessagesEntity{" +
                "id=" + id +
                ", replyMarkup='" + replyMarkup + '\'' +
                ", messageId=" + messageId +
                ", telegramBotUserEntity=" + telegramBotUserEntity +
                ", messageDate=" + messageDate +
                ", chatId=" + chatId +
                ", text='" + text + '\'' +
                ", caption='" + caption + '\'' +
                ", audWhenCreate=" + audWhenCreate +
                ", audWhenUpdate=" + audWhenUpdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelegramMessagesEntity that = (TelegramMessagesEntity) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(replyMarkup, that.replyMarkup) &&
                Objects.equal(messageId, that.messageId) &&
                Objects.equal(telegramBotUserEntity, that.telegramBotUserEntity) &&
                Objects.equal(messageDate, that.messageDate) &&
                Objects.equal(chatId, that.chatId) &&
                Objects.equal(text, that.text) &&
                Objects.equal(caption, that.caption) &&
                Objects.equal(audWhenCreate, that.audWhenCreate) &&
                Objects.equal(audWhenUpdate, that.audWhenUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, replyMarkup, messageId, telegramBotUserEntity, messageDate, chatId, text, caption, audWhenCreate, audWhenUpdate);
    }
}
