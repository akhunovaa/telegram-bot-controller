package com.botmasterzzz.controller.dto.soundcloud;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "username",
        "url",
        "avatarURL",
        "urn",
        "verified",
        "followers",
        "following"
})
public class Author implements Serializable {

    private final static long serialVersionUID = 5243856798426327200L;
    @JsonProperty("name")
    private String name;
    @JsonProperty("username")
    private String username;
    @JsonProperty("url")
    private String url;
    @JsonProperty("avatarURL")
    private String avatarURL;
    @JsonProperty("urn")
    private Integer urn;
    @JsonProperty("verified")
    private Boolean verified;
    @JsonProperty("followers")
    private Integer followers;
    @JsonProperty("following")
    private Integer following;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("avatarURL")
    public String getAvatarURL() {
        return avatarURL;
    }

    @JsonProperty("avatarURL")
    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    @JsonProperty("urn")
    public Integer getUrn() {
        return urn;
    }

    @JsonProperty("urn")
    public void setUrn(Integer urn) {
        this.urn = urn;
    }

    @JsonProperty("verified")
    public Boolean getVerified() {
        return verified;
    }

    @JsonProperty("verified")
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    @JsonProperty("followers")
    public Integer getFollowers() {
        return followers;
    }

    @JsonProperty("followers")
    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    @JsonProperty("following")
    public Integer getFollowing() {
        return following;
    }

    @JsonProperty("following")
    public void setFollowing(Integer following) {
        this.following = following;
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
        return new ToStringBuilder(this).append("name", name).append("username", username).append("url", url).append("avatarURL", avatarURL).append("urn", urn).append("verified", verified).append("followers", followers).append("following", following).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(urn).append(followers).append(avatarURL).append(following).append(name).append(verified).append(additionalProperties).append(url).append(username).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Author) == false) {
            return false;
        }
        Author rhs = ((Author) other);
        return new EqualsBuilder().append(urn, rhs.urn).append(followers, rhs.followers).append(avatarURL, rhs.avatarURL).append(following, rhs.following).append(name, rhs.name).append(verified, rhs.verified).append(additionalProperties, rhs.additionalProperties).append(url, rhs.url).append(username, rhs.username).isEquals();
    }

}
