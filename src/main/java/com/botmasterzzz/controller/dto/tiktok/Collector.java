package com.botmasterzzz.controller.dto.tiktok;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "text",
        "createTime",
        "authorMeta",
        "musicMeta",
        "covers",
        "webVideoUrl",
        "videoUrl",
        "videoUrlNoWaterMark",
        "videoMeta",
        "diggCount",
        "shareCount",
        "playCount",
        "commentCount",
        "downloaded",
        "mentions",
        "hashtags"
})
public class Collector {

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonProperty("id")
    private String id;
    @JsonProperty("text")
    private String text;
    @JsonProperty("createTime")
    private Integer createTime;
    @JsonProperty("authorMeta")
    private AuthorMeta authorMeta;
    @JsonProperty("musicMeta")
    private MusicMeta musicMeta;
    @JsonProperty("covers")
    private Covers covers;
    @JsonProperty("webVideoUrl")
    private String webVideoUrl;
    @JsonProperty("videoUrl")
    private String videoUrl;
    @JsonProperty("videoUrlNoWaterMark")
    private String videoUrlNoWaterMark;
    @JsonProperty("videoMeta")
    private VideoMeta videoMeta;
    @JsonProperty("diggCount")
    private Integer diggCount;
    @JsonProperty("shareCount")
    private Integer shareCount;
    @JsonProperty("playCount")
    private Integer playCount;
    @JsonProperty("commentCount")
    private Integer commentCount;
    @JsonProperty("downloaded")
    private Boolean downloaded;
    @JsonProperty("mentions")
    private List<Object> mentions = null;
    @JsonProperty("hashtags")
    private List<Hashtag> hashtags = null;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("createTime")
    public Integer getCreateTime() {
        return createTime;
    }

    @JsonProperty("createTime")
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @JsonProperty("authorMeta")
    public AuthorMeta getAuthorMeta() {
        return authorMeta;
    }

    @JsonProperty("authorMeta")
    public void setAuthorMeta(AuthorMeta authorMeta) {
        this.authorMeta = authorMeta;
    }

    @JsonProperty("musicMeta")
    public MusicMeta getMusicMeta() {
        return musicMeta;
    }

    @JsonProperty("musicMeta")
    public void setMusicMeta(MusicMeta musicMeta) {
        this.musicMeta = musicMeta;
    }

    @JsonProperty("covers")
    public Covers getCovers() {
        return covers;
    }

    @JsonProperty("covers")
    public void setCovers(Covers covers) {
        this.covers = covers;
    }

    @JsonProperty("webVideoUrl")
    public String getWebVideoUrl() {
        return webVideoUrl;
    }

    @JsonProperty("webVideoUrl")
    public void setWebVideoUrl(String webVideoUrl) {
        this.webVideoUrl = webVideoUrl;
    }

    @JsonProperty("videoUrl")
    public String getVideoUrl() {
        return videoUrl;
    }

    @JsonProperty("videoUrl")
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @JsonProperty("videoUrlNoWaterMark")
    public String getVideoUrlNoWaterMark() {
        return videoUrlNoWaterMark;
    }

    @JsonProperty("videoUrlNoWaterMark")
    public void setVideoUrlNoWaterMark(String videoUrlNoWaterMark) {
        this.videoUrlNoWaterMark = videoUrlNoWaterMark;
    }

    @JsonProperty("videoMeta")
    public VideoMeta getVideoMeta() {
        return videoMeta;
    }

    @JsonProperty("videoMeta")
    public void setVideoMeta(VideoMeta videoMeta) {
        this.videoMeta = videoMeta;
    }

    @JsonProperty("diggCount")
    public Integer getDiggCount() {
        return diggCount;
    }

    @JsonProperty("diggCount")
    public void setDiggCount(Integer diggCount) {
        this.diggCount = diggCount;
    }

    @JsonProperty("shareCount")
    public Integer getShareCount() {
        return shareCount;
    }

    @JsonProperty("shareCount")
    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    @JsonProperty("playCount")
    public Integer getPlayCount() {
        return playCount;
    }

    @JsonProperty("playCount")
    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    @JsonProperty("commentCount")
    public Integer getCommentCount() {
        return commentCount;
    }

    @JsonProperty("commentCount")
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    @JsonProperty("downloaded")
    public Boolean getDownloaded() {
        return downloaded;
    }

    @JsonProperty("downloaded")
    public void setDownloaded(Boolean downloaded) {
        this.downloaded = downloaded;
    }

    @JsonProperty("mentions")
    public List<Object> getMentions() {
        return mentions;
    }

    @JsonProperty("mentions")
    public void setMentions(List<Object> mentions) {
        this.mentions = mentions;
    }

    @JsonProperty("hashtags")
    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    @JsonProperty("hashtags")
    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("text", text).append("createTime", createTime).append("authorMeta", authorMeta).append("musicMeta", musicMeta).append("covers", covers).append("webVideoUrl", webVideoUrl).append("videoUrl", videoUrl).append("videoUrlNoWaterMark", videoUrlNoWaterMark).append("videoMeta", videoMeta).append("diggCount", diggCount).append("shareCount", shareCount).append("playCount", playCount).append("commentCount", commentCount).append("downloaded", downloaded).append("mentions", mentions).append("hashtags", hashtags).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(hashtags).append(videoUrlNoWaterMark).append(diggCount).append(downloaded).append(authorMeta).append(videoMeta).append(commentCount).append(shareCount).append(playCount).append(videoUrl).append(createTime).append(musicMeta).append(mentions).append(id).append(text).append(additionalProperties).append(webVideoUrl).append(covers).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Collector) == false) {
            return false;
        }
        Collector rhs = ((Collector) other);
        return new EqualsBuilder().append(hashtags, rhs.hashtags).append(videoUrlNoWaterMark, rhs.videoUrlNoWaterMark).append(diggCount, rhs.diggCount).append(downloaded, rhs.downloaded).append(authorMeta, rhs.authorMeta).append(videoMeta, rhs.videoMeta).append(commentCount, rhs.commentCount).append(shareCount, rhs.shareCount).append(playCount, rhs.playCount).append(videoUrl, rhs.videoUrl).append(createTime, rhs.createTime).append(musicMeta, rhs.musicMeta).append(mentions, rhs.mentions).append(id, rhs.id).append(text, rhs.text).append(additionalProperties, rhs.additionalProperties).append(webVideoUrl, rhs.webVideoUrl).append(covers, rhs.covers).isEquals();
    }

}
