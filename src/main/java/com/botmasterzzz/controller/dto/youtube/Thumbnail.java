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
        "thumbnails"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Thumbnail implements Serializable {

    @JsonProperty("thumbnails")
    private List<Thumbnail_> thumbnails = null;

    public Thumbnail() {
    }

    @JsonProperty("thumbnails")
    public List<Thumbnail_> getThumbnails() {
        return thumbnails;
    }

    @JsonProperty("thumbnails")
    public void setThumbnails(List<Thumbnail_> thumbnails) {
        this.thumbnails = thumbnails;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("thumbnails", thumbnails).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(thumbnails).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Thumbnail) == false) {
            return false;
        }
        Thumbnail rhs = ((Thumbnail) other);
        return new EqualsBuilder().append(thumbnails, rhs.thumbnails).isEquals();
    }

}
