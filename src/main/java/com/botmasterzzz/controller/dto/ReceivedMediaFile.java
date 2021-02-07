package com.botmasterzzz.controller.dto;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class ReceivedMediaFile implements Serializable {

    private String id;
    private String title;
    private String description;
    private List<File> file;
    private String fileId;
    private String musicId;
    private Long width;
    private Long height;
    private String videoUrl;
    private String videoWithoutWatermarkUrl;
    private String coverThumb;
    private String coverUrl;
    private String authorName;
    private String authorNickName;
    private Long duration;
    private Long createTime;

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

    public List<File> getFile() {
        return file;
    }

    public void setFile(List<File> file) {
        this.file = file;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorNickName() {
        return authorNickName;
    }

    public void setAuthorNickName(String authorNickName) {
        this.authorNickName = authorNickName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getVideoWithoutWatermarkUrl() {
        return videoWithoutWatermarkUrl;
    }

    public void setVideoWithoutWatermarkUrl(String videoWithoutWatermarkUrl) {
        this.videoWithoutWatermarkUrl = videoWithoutWatermarkUrl;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getCoverThumb() {
        return coverThumb;
    }

    public void setCoverThumb(String coverThumb) {
        this.coverThumb = coverThumb;
    }

    @Override
    public String toString() {
        return "ReceivedMediaFile{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", file=" + file +
                ", fileId='" + fileId + '\'' +
                ", musicId='" + musicId + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoWithoutWatermarkUrl='" + videoWithoutWatermarkUrl + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorNickName='" + authorNickName + '\'' +
                ", coverThumb=" + coverThumb +
                ", duration=" + duration +
                ", createTime=" + createTime +
                '}';
    }
}
