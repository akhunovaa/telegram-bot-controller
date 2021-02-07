package com.botmasterzzz.controller.dto;

import com.google.common.base.Objects;

import java.sql.Timestamp;

public class MostActualMediaDTO {

    private long count;
    private String fileId;
    private long mediaId;
    private int fileType;
    private int width;
    private int height;
    private boolean isAnon;
    private String message;
    private Timestamp audWhenCreate;
    private long userId;
    private long telegramUserId;
    private String telegramUserName;
    private String telegramFirstName;
    private String telegramLastName;
    private boolean isAdmin;
    private boolean isPremium;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public long getMediaId() {
        return mediaId;
    }

    public void setMediaId(long mediaId) {
        this.mediaId = mediaId;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public boolean isAnon() {
        return isAnon;
    }

    public void setAnon(boolean anon) {
        isAnon = anon;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getAudWhenCreate() {
        return audWhenCreate;
    }

    public void setAudWhenCreate(Timestamp audWhenCreate) {
        this.audWhenCreate = audWhenCreate;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    @Override
    public String toString() {
        return "MostActualMediaDTO{" +
                "count=" + count +
                ", fileId='" + fileId + '\'' +
                ", mediaId=" + mediaId +
                ", fileType=" + fileType +
                ", width=" + width +
                ", height=" + height +
                ", isAnon=" + isAnon +
                ", message='" + message + '\'' +
                ", audWhenCreate=" + audWhenCreate +
                ", userId=" + userId +
                ", telegramUserId=" + telegramUserId +
                ", telegramUserName='" + telegramUserName + '\'' +
                ", telegramFirstName='" + telegramFirstName + '\'' +
                ", telegramLastName='" + telegramLastName + '\'' +
                ", isAdmin=" + isAdmin +
                ", isPremium=" + isPremium +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MostActualMediaDTO that = (MostActualMediaDTO) o;
        return count == that.count &&
                mediaId == that.mediaId &&
                fileType == that.fileType &&
                width == that.width &&
                height == that.height &&
                isAnon == that.isAnon &&
                userId == that.userId &&
                telegramUserId == that.telegramUserId &&
                isAdmin == that.isAdmin &&
                isPremium == that.isPremium &&
                Objects.equal(fileId, that.fileId) &&
                Objects.equal(message, that.message) &&
                Objects.equal(audWhenCreate, that.audWhenCreate) &&
                Objects.equal(telegramUserName, that.telegramUserName) &&
                Objects.equal(telegramFirstName, that.telegramFirstName) &&
                Objects.equal(telegramLastName, that.telegramLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(count, fileId, mediaId, fileType, width, height, isAnon, message, audWhenCreate, userId, telegramUserId, telegramUserName, telegramFirstName, telegramLastName, isAdmin, isPremium);
    }
}
