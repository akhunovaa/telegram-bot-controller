package com.botmasterzzz.controller.dto.instagram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InstagramMediaItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private long pk;
    private String id;
    private String mediaType;
    private int videoDuration;
    private String code;
    private String caption;
    private InstagramMediaAuthor instagramMediaAuthor;
    private InstagramMediaLocation instagramMediaLocation;
    private List<InstagramMediaVideo> instagramMediaVideoList;
    private List<InstagramMediaImage> instagramMediaImageList;

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public int getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(int videoDuration) {
        this.videoDuration = videoDuration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public InstagramMediaAuthor getInstagramMediaAuthor() {
        return instagramMediaAuthor;
    }

    public void setInstagramMediaAuthor(InstagramMediaAuthor instagramMediaAuthor) {
        this.instagramMediaAuthor = instagramMediaAuthor;
    }

    public InstagramMediaLocation getInstagramMediaLocation() {
        return instagramMediaLocation;
    }

    public void setInstagramMediaLocation(InstagramMediaLocation instagramMediaLocation) {
        this.instagramMediaLocation = instagramMediaLocation;
    }

    public List<InstagramMediaVideo> getInstagramMediaVideoList() {
        return instagramMediaVideoList;
    }

    public void setInstagramMediaVideoList(List<InstagramMediaVideo> instagramMediaVideoList) {
        this.instagramMediaVideoList = instagramMediaVideoList;
    }

    public List<InstagramMediaImage> getInstagramMediaImageList() {
        return instagramMediaImageList;
    }

    public void setInstagramMediaImageList(List<InstagramMediaImage> instagramMediaImageList) {
        this.instagramMediaImageList = instagramMediaImageList;
    }

    @Override
    public String toString() {
        return "InstagramMediaItem{" +
                "pk=" + pk +
                ", id='" + id + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", videoDuration=" + videoDuration +
                ", code='" + code + '\'' +
                ", caption='" + caption + '\'' +
                ", instagramMediaAuthor=" + instagramMediaAuthor +
                ", instagramMediaLocation=" + instagramMediaLocation +
                ", instagramMediaVideoList=" + instagramMediaVideoList +
                ", instagramMediaImageList=" + instagramMediaImageList +
                '}';
    }
}
