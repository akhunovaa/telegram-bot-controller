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
        "headers",
        "collector"
})
public class TikTokMediaDataDTO {

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonProperty("headers")
    private Headers headers;
    @JsonProperty("collector")
    private List<Collector> collector = null;

    @JsonProperty("headers")
    public Headers getHeaders() {
        return headers;
    }

    @JsonProperty("headers")
    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    @JsonProperty("collector")
    public List<Collector> getCollector() {
        return collector;
    }

    @JsonProperty("collector")
    public void setCollector(List<Collector> collector) {
        this.collector = collector;
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
        return new ToStringBuilder(this).append("headers", headers).append("collector", collector).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(headers).append(additionalProperties).append(collector).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TikTokMediaDataDTO) == false) {
            return false;
        }
        TikTokMediaDataDTO rhs = ((TikTokMediaDataDTO) other);
        return new EqualsBuilder().append(headers, rhs.headers).append(additionalProperties, rhs.additionalProperties).append(collector, rhs.collector).isEquals();
    }

}
