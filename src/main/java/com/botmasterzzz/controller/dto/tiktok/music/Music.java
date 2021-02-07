package com.botmasterzzz.controller.dto.tiktok.music;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "title",
        "playUrl",
        "coverThumb",
        "coverMedium",
        "coverLarge",
        "authorName",
        "original",
        "playToken",
        "keyToken",
        "audioURLWithCookie",
        "private",
        "duration",
        "album"
})
public class Music {

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("playUrl")
    private String playUrl;
    @JsonProperty("coverThumb")
    private String coverThumb;
    @JsonProperty("coverMedium")
    private String coverMedium;
    @JsonProperty("coverLarge")
    private String coverLarge;
    @JsonProperty("authorName")
    private String authorName;
    @JsonProperty("original")
    private Boolean original;
    @JsonProperty("playToken")
    private String playToken;
    @JsonProperty("keyToken")
    private String keyToken;
    @JsonProperty("audioURLWithCookie")
    private Boolean audioURLWithCookie;
    @JsonProperty("private")
    private Boolean _private;
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("album")
    private String album;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
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

    @JsonProperty("authorName")
    public String getAuthorName() {
        return authorName;
    }

    @JsonProperty("authorName")
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @JsonProperty("original")
    public Boolean getOriginal() {
        return original;
    }

    @JsonProperty("original")
    public void setOriginal(Boolean original) {
        this.original = original;
    }

    @JsonProperty("playToken")
    public String getPlayToken() {
        return playToken;
    }

    @JsonProperty("playToken")
    public void setPlayToken(String playToken) {
        this.playToken = playToken;
    }

    @JsonProperty("keyToken")
    public String getKeyToken() {
        return keyToken;
    }

    @JsonProperty("keyToken")
    public void setKeyToken(String keyToken) {
        this.keyToken = keyToken;
    }

    @JsonProperty("audioURLWithCookie")
    public Boolean getAudioURLWithCookie() {
        return audioURLWithCookie;
    }

    @JsonProperty("audioURLWithCookie")
    public void setAudioURLWithCookie(Boolean audioURLWithCookie) {
        this.audioURLWithCookie = audioURLWithCookie;
    }

    @JsonProperty("private")
    public Boolean getPrivate() {
        return _private;
    }

    @JsonProperty("private")
    public void setPrivate(Boolean _private) {
        this._private = _private;
    }

    @JsonProperty("duration")
    public Integer getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @JsonProperty("album")
    public String getAlbum() {
        return album;
    }

    @JsonProperty("album")
    public void setAlbum(String album) {
        this.album = album;
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
        return new ToStringBuilder(this).append("id", id).append("title", title).append("playUrl", playUrl).append("coverThumb", coverThumb).append("coverMedium", coverMedium).append("coverLarge", coverLarge).append("authorName", authorName).append("original", original).append("playToken", playToken).append("keyToken", keyToken).append("audioURLWithCookie", audioURLWithCookie).append("_private", _private).append("duration", duration).append("album", album).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(original).append(album).append(title).append(playUrl).append(duration).append(coverThumb).append(_private).append(audioURLWithCookie).append(authorName).append(coverMedium).append(id).append(keyToken).append(additionalProperties).append(coverLarge).append(playToken).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Music) == false) {
            return false;
        }
        Music rhs = ((Music) other);
        return new EqualsBuilder().append(original, rhs.original).append(album, rhs.album).append(title, rhs.title).append(playUrl, rhs.playUrl).append(duration, rhs.duration).append(coverThumb, rhs.coverThumb).append(_private, rhs._private).append(audioURLWithCookie, rhs.audioURLWithCookie).append(authorName, rhs.authorName).append(coverMedium, rhs.coverMedium).append(id, rhs.id).append(keyToken, rhs.keyToken).append(additionalProperties, rhs.additionalProperties).append(coverLarge, rhs.coverLarge).append(playToken, rhs.playToken).isEquals();
    }

}
