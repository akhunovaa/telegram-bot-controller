package com.botmasterzzz.controller.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "telegram_bot_users")
public class TelegramBotUserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "telegram_id")
    private Long telegramId;

    @Column(name = "is_bot")
    private boolean isBot;

    @Column(name = "language_code")
    private String languageCode;

    @Column(name = "local_name")
    private String localName;

    @JsonIgnore
    @Column(name = "note")
    private String note;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @Column(name = "is_premium")
    private boolean isPremium;

    @Column(name = "referral_id")
    private Long referralId;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean bot) {
        isBot = bot;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Long getReferralId() {
        return referralId;
    }

    public void setReferralId(Long referralId) {
        this.referralId = referralId;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    @Override
    public String toString() {
        return "TelegramBotUserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", localName='" + localName + '\'' +
                ", telegramId=" + telegramId +
                ", isBot=" + isBot +
                ", languageCode='" + languageCode + '\'' +
                ", note='" + note + '\'' +
                ", isBlocked=" + isBlocked +
                ", isAdmin=" + isAdmin +
                ", isPremium=" + isPremium +
                ", referralId=" + referralId +
                ", audWhenCreate=" + audWhenCreate +
                ", audWhenUpdate=" + audWhenUpdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelegramBotUserEntity that = (TelegramBotUserEntity) o;
        return isBot == that.isBot &&
                isBlocked == that.isBlocked &&
                isAdmin == that.isAdmin &&
                isPremium == that.isPremium &&
                Objects.equal(id, that.id) &&
                Objects.equal(firstName, that.firstName) &&
                Objects.equal(lastName, that.lastName) &&
                Objects.equal(username, that.username) &&
                Objects.equal(localName, that.localName) &&
                Objects.equal(telegramId, that.telegramId) &&
                Objects.equal(languageCode, that.languageCode) &&
                Objects.equal(note, that.note) &&
                Objects.equal(referralId, that.referralId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, firstName, lastName, username, localName, telegramId, isBot, languageCode, note, isBlocked, isAdmin, isPremium, referralId);
    }
}
