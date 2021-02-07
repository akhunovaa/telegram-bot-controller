package com.botmasterzzz.controller.entity;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "received_media_data")
public class ReceivedMediaDataEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "no_watermark_video_url")
    private String noWaterMarkVideoUrl;

    @Column(name = "location_path")
    private String locationPath;

    @Column(name = "height")
    private int height;

    @Column(name = "width")
    private int width;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "duration")
    private int duration;

    @Column(name = "aud_when_create")
    private Timestamp audWhenCreate;

    @Column(name = "aud_when_update")
    private Timestamp audWhenUpdate;

    @Column(name = "creation_date")
    private Long creationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getLocationPath() {
        return locationPath;
    }

    public void setLocationPath(String locationPath) {
        this.locationPath = locationPath;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public String getNoWaterMarkVideoUrl() {
        return noWaterMarkVideoUrl;
    }

    public void setNoWaterMarkVideoUrl(String noWaterMarkVideoUrl) {
        this.noWaterMarkVideoUrl = noWaterMarkVideoUrl;
    }

    @Override
    public String toString() {
        return "ReceivedMediaDataEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", noWaterMarkVideoUrl='" + noWaterMarkVideoUrl + '\'' +
                ", locationPath='" + locationPath + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", coverUrl='" + coverUrl + '\'' +
                ", duration=" + duration +
                ", audWhenCreate=" + audWhenCreate +
                ", audWhenUpdate=" + audWhenUpdate +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceivedMediaDataEntity that = (ReceivedMediaDataEntity) o;
        return height == that.height &&
                width == that.width &&
                duration == that.duration &&
                Objects.equal(id, that.id) &&
                Objects.equal(title, that.title) &&
                Objects.equal(description, that.description) &&
                Objects.equal(videoUrl, that.videoUrl) &&
                Objects.equal(noWaterMarkVideoUrl, that.noWaterMarkVideoUrl) &&
                Objects.equal(locationPath, that.locationPath) &&
                Objects.equal(coverUrl, that.coverUrl) &&
                Objects.equal(audWhenCreate, that.audWhenCreate) &&
                Objects.equal(audWhenUpdate, that.audWhenUpdate) &&
                Objects.equal(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, title, description, videoUrl, noWaterMarkVideoUrl, locationPath, height, width, coverUrl, duration, audWhenCreate, audWhenUpdate, creationDate);
    }
}
