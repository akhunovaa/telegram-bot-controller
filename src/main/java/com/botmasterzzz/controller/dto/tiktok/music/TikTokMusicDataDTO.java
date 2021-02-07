package com.botmasterzzz.controller.dto.tiktok.music;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "music",
        "author",
        "stats",
        "shareMeta"
})
public class TikTokMusicDataDTO {

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonProperty("music")
    private Music music;
    @JsonProperty("author")
    private Author author;
    @JsonProperty("stats")
    private Stats stats;
    @JsonProperty("shareMeta")
    private ShareMeta shareMeta;

    @JsonProperty("music")
    public Music getMusic() {
        return music;
    }

    @JsonProperty("music")
    public void setMusic(Music music) {
        this.music = music;
    }

    @JsonProperty("author")
    public Author getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(Author author) {
        this.author = author;
    }

    @JsonProperty("stats")
    public Stats getStats() {
        return stats;
    }

    @JsonProperty("stats")
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @JsonProperty("shareMeta")
    public ShareMeta getShareMeta() {
        return shareMeta;
    }

    @JsonProperty("shareMeta")
    public void setShareMeta(ShareMeta shareMeta) {
        this.shareMeta = shareMeta;
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
        return new ToStringBuilder(this).append("music", music).append("author", author).append("stats", stats).append("shareMeta", shareMeta).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(music).append(additionalProperties).append(shareMeta).append(stats).append(author).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TikTokMusicDataDTO) == false) {
            return false;
        }
        TikTokMusicDataDTO rhs = ((TikTokMusicDataDTO) other);
        return new EqualsBuilder().append(music, rhs.music).append(additionalProperties, rhs.additionalProperties).append(shareMeta, rhs.shareMeta).append(stats, rhs.stats).append(author, rhs.author).isEquals();
    }

}
