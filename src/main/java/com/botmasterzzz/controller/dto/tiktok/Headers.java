package com.botmasterzzz.controller.dto.tiktok;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "User-Agent",
        "Referer",
        "Cookie"
})
public class Headers {

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonProperty("User-Agent")
    private String userAgent;
    @JsonProperty("Referer")
    private String referer;
    @JsonProperty("Cookie")
    private String cookie;

    @JsonProperty("User-Agent")
    public String getUserAgent() {
        return userAgent;
    }

    @JsonProperty("User-Agent")
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @JsonProperty("Referer")
    public String getReferer() {
        return referer;
    }

    @JsonProperty("Referer")
    public void setReferer(String referer) {
        this.referer = referer;
    }

    @JsonProperty("Cookie")
    public String getCookie() {
        return cookie;
    }

    @JsonProperty("Cookie")
    public void setCookie(String cookie) {
        this.cookie = cookie;
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
        return new ToStringBuilder(this).append("userAgent", userAgent).append("referer", referer).append("cookie", cookie).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(referer).append(userAgent).append(additionalProperties).append(cookie).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Headers) == false) {
            return false;
        }
        Headers rhs = ((Headers) other);
        return new EqualsBuilder().append(referer, rhs.referer).append(userAgent, rhs.userAgent).append(additionalProperties, rhs.additionalProperties).append(cookie, rhs.cookie).isEquals();
    }

}
