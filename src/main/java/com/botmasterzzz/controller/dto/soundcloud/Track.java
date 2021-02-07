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
        "hls",
        "progressive"
})
public class Track implements Serializable {

    private final static long serialVersionUID = 4583350506956513678L;
    @JsonProperty("hls")
    private String hls;
    @JsonProperty("progressive")
    private String progressive;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("hls")
    public String getHls() {
        return hls;
    }

    @JsonProperty("hls")
    public void setHls(String hls) {
        this.hls = hls;
    }

    @JsonProperty("progressive")
    public String getProgressive() {
        return progressive;
    }

    @JsonProperty("progressive")
    public void setProgressive(String progressive) {
        this.progressive = progressive;
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
        return new ToStringBuilder(this).append("hls", hls).append("progressive", progressive).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(progressive).append(additionalProperties).append(hls).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Track) == false) {
            return false;
        }
        Track rhs = ((Track) other);
        return new EqualsBuilder().append(progressive, rhs.progressive).append(additionalProperties, rhs.additionalProperties).append(hls, rhs.hls).isEquals();
    }

}
