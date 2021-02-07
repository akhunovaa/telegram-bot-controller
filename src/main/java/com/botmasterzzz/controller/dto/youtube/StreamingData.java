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
        "expiresInSeconds",
        "formats",
        "adaptiveFormats"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingData implements Serializable {

    @JsonProperty("expiresInSeconds")
    private String expiresInSeconds;
    @JsonProperty("formats")
    private List<Format> formats = null;
    @JsonProperty("adaptiveFormats")
    private List<AdaptiveFormat> adaptiveFormats = null;
    public StreamingData() {
    }

    @JsonProperty("expiresInSeconds")
    public String getExpiresInSeconds() {
        return expiresInSeconds;
    }

    @JsonProperty("expiresInSeconds")
    public void setExpiresInSeconds(String expiresInSeconds) {
        this.expiresInSeconds = expiresInSeconds;
    }

    @JsonProperty("formats")
    public List<Format> getFormats() {
        return formats;
    }

    @JsonProperty("formats")
    public void setFormats(List<Format> formats) {
        this.formats = formats;
    }

    @JsonProperty("adaptiveFormats")
    public List<AdaptiveFormat> getAdaptiveFormats() {
        return adaptiveFormats;
    }

    @JsonProperty("adaptiveFormats")
    public void setAdaptiveFormats(List<AdaptiveFormat> adaptiveFormats) {
        this.adaptiveFormats = adaptiveFormats;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("expiresInSeconds", expiresInSeconds).append("formats", formats).append("adaptiveFormats", adaptiveFormats).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(formats).append(adaptiveFormats).append(expiresInSeconds).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StreamingData) == false) {
            return false;
        }
        StreamingData rhs = ((StreamingData) other);
        return new EqualsBuilder().append(formats, rhs.formats).append(adaptiveFormats, rhs.adaptiveFormats).append(expiresInSeconds, rhs.expiresInSeconds).isEquals();
    }

}
