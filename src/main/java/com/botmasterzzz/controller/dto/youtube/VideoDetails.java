package com.botmasterzzz.controller.dto.youtube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "videoId",
        "title",
        "lengthSeconds",
        "keywords",
        "channelId",
        "shortDescription",
        "thumbnail",
        "viewCount",
        "author"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoDetails implements Serializable {

    @JsonProperty("videoId")
    private String videoId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("lengthSeconds")
    private String lengthSeconds;
    @JsonProperty("keywords")
    private List<String> keywords = null;
    @JsonProperty("channelId")
    private String channelId;
    @JsonProperty("shortDescription")
    private String shortDescription;
    @JsonProperty("thumbnail")
    private Thumbnail thumbnail;
    @JsonProperty("viewCount")
    private String viewCount;
    @JsonProperty("author")
    private String author;
    public VideoDetails() {
    }

    @JsonProperty("videoId")
    public String getVideoId() {
        return videoId;
    }

    @JsonProperty("videoId")
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("lengthSeconds")
    public String getLengthSeconds() {
        return lengthSeconds;
    }

    @JsonProperty("lengthSeconds")
    public void setLengthSeconds(String lengthSeconds) {
        this.lengthSeconds = lengthSeconds;
    }

    @JsonProperty("keywords")
    public List<String> getKeywords() {
        return keywords;
    }

    @JsonProperty("keywords")
    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    @JsonProperty("channelId")
    public String getChannelId() {
        return channelId;
    }

    @JsonProperty("channelId")
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @JsonProperty("shortDescription")
    public String getShortDescription() {
        return shortDescription;
    }

    @JsonProperty("shortDescription")
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @JsonProperty("thumbnail")
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("viewCount")
    public String getViewCount() {
        return viewCount;
    }

    @JsonProperty("viewCount")
    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("videoId", videoId).append("title", title).append("lengthSeconds", lengthSeconds).append("keywords", keywords).append("channelId", channelId).append("shortDescription", shortDescription).append("thumbnail", thumbnail).append("viewCount", viewCount).append("author", author).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(thumbnail).append(keywords).append(author).append(lengthSeconds).append(videoId).append(shortDescription).append(viewCount).append(title).append(channelId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VideoDetails) == false) {
            return false;
        }
        VideoDetails rhs = ((VideoDetails) other);
        return new EqualsBuilder().append(thumbnail, rhs.thumbnail).append(keywords, rhs.keywords).append(author, rhs.author).append(lengthSeconds, rhs.lengthSeconds).append(videoId, rhs.videoId).append(shortDescription, rhs.shortDescription).append(viewCount, rhs.viewCount).append(title, rhs.title).append(channelId, rhs.channelId).isEquals();
    }

}
