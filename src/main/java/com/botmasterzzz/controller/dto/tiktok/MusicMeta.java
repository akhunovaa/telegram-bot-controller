package com.botmasterzzz.controller.dto.tiktok;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "musicId",
        "musicName",
        "musicAuthor",
        "musicOriginal",
        "playUrl",
        "coverThumb",
        "coverMedium",
        "coverLarge"
})
public class MusicMeta {

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonProperty("musicId")
    private String musicId;
    @JsonProperty("musicName")
    private String musicName;
    @JsonProperty("musicAuthor")
    private String musicAuthor;
    @JsonProperty("musicOriginal")
    private Boolean musicOriginal;
    @JsonProperty("playUrl")
    private String playUrl;
    @JsonProperty("coverThumb")
    private String coverThumb;
    @JsonProperty("coverMedium")
    private String coverMedium;
    @JsonProperty("coverLarge")
    private String coverLarge;

    @JsonProperty("musicId")
    public String getMusicId() {
        return musicId;
    }

    @JsonProperty("musicId")
    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    @JsonProperty("musicName")
    public String getMusicName() {
        return musicName;
    }

    @JsonProperty("musicName")
    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    @JsonProperty("musicAuthor")
    public String getMusicAuthor() {
        return musicAuthor;
    }

    @JsonProperty("musicAuthor")
    public void setMusicAuthor(String musicAuthor) {
        this.musicAuthor = musicAuthor;
    }

    @JsonProperty("musicOriginal")
    public Boolean getMusicOriginal() {
        return musicOriginal;
    }

    @JsonProperty("musicOriginal")
    public void setMusicOriginal(Boolean musicOriginal) {
        this.musicOriginal = musicOriginal;
    }

    @JsonProperty("playUrl")
    public String getPlayUrl() {
        return playUrl;
    }

    @JsonProperty("playUrl")
    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    @JsonProperty("coverThumb")
    public String getCoverThumb() {
        return coverThumb;
    }

    @JsonProperty("coverThumb")
    public void setCoverThumb(String coverThumb) {
        this.coverThumb = coverThumb;
    }

    @JsonProperty("coverMedium")
    public String getCoverMedium() {
        return coverMedium;
    }

    @JsonProperty("coverMedium")
    public void setCoverMedium(String coverMedium) {
        this.coverMedium = coverMedium;
    }

    @JsonProperty("coverLarge")
    public String getCoverLarge() {
        return coverLarge;
    }

    @JsonProperty("coverLarge")
    public void setCoverLarge(String coverLarge) {
        this.coverLarge = coverLarge;
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
        return new ToStringBuilder(this).append("musicId", musicId).append("musicName", musicName).append("musicAuthor", musicAuthor).append("musicOriginal", musicOriginal).append("playUrl", playUrl).append("coverThumb", coverThumb).append("coverMedium", coverMedium).append("coverLarge", coverLarge).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(coverThumb).append(musicId).append(musicAuthor).append(coverMedium).append(additionalProperties).append(coverLarge).append(musicName).append(musicOriginal).append(playUrl).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MusicMeta) == false) {
            return false;
        }
        MusicMeta rhs = ((MusicMeta) other);
        return new EqualsBuilder().append(coverThumb, rhs.coverThumb).append(musicId, rhs.musicId).append(musicAuthor, rhs.musicAuthor).append(coverMedium, rhs.coverMedium).append(additionalProperties, rhs.additionalProperties).append(coverLarge, rhs.coverLarge).append(musicName, rhs.musicName).append(musicOriginal, rhs.musicOriginal).append(playUrl, rhs.playUrl).isEquals();
    }

}
