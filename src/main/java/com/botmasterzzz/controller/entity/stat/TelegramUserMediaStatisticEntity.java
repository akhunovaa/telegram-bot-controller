package com.botmasterzzz.controller.entity.stat;

import com.botmasterzzz.controller.entity.TelegramBotUserEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "telegram_user_media")
public class TelegramUserMediaStatisticEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "file_id")
    private String fileId;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "file_size")
    private Integer fileSize;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_type")
    private Integer fileType;

    @Column(name = "is_anon")
    private boolean isAnon;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "message")
    private String message;

    @JoinColumn(name = "telegram_user_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private TelegramBotUserEntity telegramBotUserEntity;

    @OneToMany(mappedBy = "telegramUserMediaStatisticEntity", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<TelegramMedia4TopStatisticEntity> telegramMedia4TopStatisticEntity;

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

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public boolean isAnon() {
        return isAnon;
    }

    public void setAnon(boolean anon) {
        isAnon = anon;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TelegramBotUserEntity getTelegramBotUserEntity() {
        return telegramBotUserEntity;
    }

    public void setTelegramBotUserEntity(TelegramBotUserEntity telegramBotUserEntity) {
        this.telegramBotUserEntity = telegramBotUserEntity;
    }

    public List<TelegramMedia4TopStatisticEntity> getTelegramMedia4TopStatisticEntity() {
        return telegramMedia4TopStatisticEntity;
    }

    public void setTelegramMedia4TopStatisticEntity(List<TelegramMedia4TopStatisticEntity> telegramMedia4TopStatisticEntity) {
        this.telegramMedia4TopStatisticEntity = telegramMedia4TopStatisticEntity;
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
        return "TelegramUserMediaStatisticEntity{" +
                "id=" + id +
                ", fileId='" + fileId + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", fileSize=" + fileSize +
                ", filePath='" + filePath + '\'' +
                ", fileType=" + fileType +
                ", isAnon=" + isAnon +
                ", isDeleted=" + isDeleted +
                ", message='" + message + '\'' +
                ", telegramBotUserEntity=" + telegramBotUserEntity +
                ", telegramMedia4TopStatisticEntity=" + telegramMedia4TopStatisticEntity +
                ", audWhenCreate=" + audWhenCreate +
                ", audWhenUpdate=" + audWhenUpdate +
                '}';
    }
}
