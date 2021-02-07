package com.botmasterzzz.controller.dto.soundcloud;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "title",
        "description",
        "thumbnail",
        "url",
        "duration",
        "playCount",
        "commentsCount",
        "likes",
        "genre",
        "author",
        "publishedAt",
        "embedURL",
        "embed",
        "track",
        "trackURL",
        "comments",
        "streamURL"
})
public class SoundCloudDTO implements Serializable {

    private final static long serialVersionUID = 3176686976611531626L;
    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("url")
    private String url;
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("playCount")
    private String playCount;
    @JsonProperty("commentsCount")
    private String commentsCount;
    @JsonProperty("likes")
    private String likes;
    @JsonProperty("genre")
    private String genre;
    @JsonProperty("author")
    private Author author;
    @JsonProperty("publishedAt")
    private String publishedAt;
    @JsonProperty("embedURL")
    private String embedURL;
    @JsonProperty("embed")
    private Object embed;
    @JsonProperty("track")
    private Track track;
    @JsonProperty("trackURL")
    private String trackURL;
    @JsonProperty("comments")
    private List<Object> comments = null;
    @JsonProperty("streamURL")
    private String streamURL;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("duration")
    public Integer getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @JsonProperty("playCount")
    public String getPlayCount() {
        return playCount;
    }

    @JsonProperty("playCount")
    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    @JsonProperty("commentsCount")
    public String getCommentsCount() {
        return commentsCount;
    }

    @JsonProperty("commentsCount")
    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    @JsonProperty("likes")
    public String getLikes() {
        return likes;
    }

    @JsonProperty("likes")
    public void setLikes(String likes) {
        this.likes = likes;
    }

    @JsonProperty("genre")
    public String getGenre() {
        return genre;
    }

    @JsonProperty("genre")
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @JsonProperty("author")
    public Author getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(Author author) {
        this.author = author;
    }

    @JsonProperty("publishedAt")
    public String getPublishedAt() {
        return publishedAt;
    }

    @JsonProperty("publishedAt")
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @JsonProperty("embedURL")
    public String getEmbedURL() {
        return embedURL;
    }

    @JsonProperty("embedURL")
    public void setEmbedURL(String embedURL) {
        this.embedURL = embedURL;
    }

    @JsonProperty("embed")
    public Object getEmbed() {
        return embed;
    }

    @JsonProperty("embed")
    public void setEmbed(Object embed) {
        this.embed = embed;
    }

    @JsonProperty("track")
    public Track getTrack() {
        return track;
    }

    @JsonProperty("track")
    public void setTrack(Track track) {
        this.track = track;
    }

    @JsonProperty("trackURL")
    public String getTrackURL() {
        return trackURL;
    }

    @JsonProperty("trackURL")
    public void setTrackURL(String trackURL) {
        this.trackURL = trackURL;
    }

    @JsonProperty("comments")
    public List<Object> getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(List<Object> comments) {
        this.comments = comments;
    }

    @JsonProperty("streamURL")
    public String getStreamURL() {
        return streamURL;
    }

    @JsonProperty("streamURL")
    public void setStreamURL(String streamURL) {
        this.streamURL = streamURL;
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
        return new ToStringBuilder(this).append("id", id).append("title", title).append("description", description).append("thumbnail", thumbnail).append("url", url).append("duration", duration).append("playCount", playCount).append("commentsCount", commentsCount).append("likes", likes).append("genre", genre).append("author", author).append("publishedAt", publishedAt).append("embedURL", embedURL).append("embed", embed).append("track", track).append("trackURL", trackURL).append("comments", comments).append("streamURL", streamURL).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(embedURL).append(streamURL).append(thumbnail).append(comments).append(publishedAt).append(author).append(description).append(title).append(url).append(duration).append(playCount).append(commentsCount).append(genre).append(id).append(embed).append(additionalProperties).append(track).append(trackURL).append(likes).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SoundCloudDTO) == false) {
            return false;
        }
        SoundCloudDTO rhs = ((SoundCloudDTO) other);
        return new EqualsBuilder().append(embedURL, rhs.embedURL).append(streamURL, rhs.streamURL).append(thumbnail, rhs.thumbnail).append(comments, rhs.comments).append(publishedAt, rhs.publishedAt).append(author, rhs.author).append(description, rhs.description).append(title, rhs.title).append(url, rhs.url).append(duration, rhs.duration).append(playCount, rhs.playCount).append(commentsCount, rhs.commentsCount).append(genre, rhs.genre).append(id, rhs.id).append(embed, rhs.embed).append(additionalProperties, rhs.additionalProperties).append(track, rhs.track).append(trackURL, rhs.trackURL).append(likes, rhs.likes).isEquals();
    }

}
