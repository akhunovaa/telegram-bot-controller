package com.botmasterzzz.controller.dto.youtube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "streamingData",
        "videoDetails"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeMediaDTO implements Serializable {

    @JsonProperty("streamingData")
    private StreamingData streamingData;
    @JsonProperty("videoDetails")
    private VideoDetails videoDetails;
    public YouTubeMediaDTO() {
    }

    @JsonProperty("streamingData")
    public StreamingData getStreamingData() {
        return streamingData;
    }

    @JsonProperty("streamingData")
    public void setStreamingData(StreamingData streamingData) {
        this.streamingData = streamingData;
    }

    @JsonProperty("videoDetails")
    public VideoDetails getVideoDetails() {
        return videoDetails;
    }

    @JsonProperty("videoDetails")
    public void setVideoDetails(VideoDetails videoDetails) {
        this.videoDetails = videoDetails;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("streamingData", streamingData).append("videoDetails", videoDetails).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(videoDetails).append(streamingData).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof YouTubeMediaDTO)) {
            return false;
        }
        YouTubeMediaDTO rhs = ((YouTubeMediaDTO) other);
        return new EqualsBuilder().append(videoDetails, rhs.videoDetails).append(streamingData, rhs.streamingData).isEquals();
    }

}
