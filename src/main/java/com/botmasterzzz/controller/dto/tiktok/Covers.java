package com.botmasterzzz.controller.dto.tiktok;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "default",
        "origin",
        "dynamic"
})
public class Covers {

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonProperty("default")
    private String _default;
    @JsonProperty("origin")
    private String origin;
    @JsonProperty("dynamic")
    private String dynamic;

    @JsonProperty("default")
    public String getDefault() {
        return _default;
    }

    @JsonProperty("default")
    public void setDefault(String _default) {
        this._default = _default;
    }

    @JsonProperty("origin")
    public String getOrigin() {
        return origin;
    }

    @JsonProperty("origin")
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @JsonProperty("dynamic")
    public String getDynamic() {
        return dynamic;
    }

    @JsonProperty("dynamic")
    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
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
        return new ToStringBuilder(this).append("_default", _default).append("origin", origin).append("dynamic", dynamic).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(_default).append(dynamic).append(additionalProperties).append(origin).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Covers) == false) {
            return false;
        }
        Covers rhs = ((Covers) other);
        return new EqualsBuilder().append(_default, rhs._default).append(dynamic, rhs.dynamic).append(additionalProperties, rhs.additionalProperties).append(origin, rhs.origin).isEquals();
    }

}
