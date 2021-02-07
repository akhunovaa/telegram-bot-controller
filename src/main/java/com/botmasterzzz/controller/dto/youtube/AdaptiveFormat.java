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
        "itag",
        "url",
        "mimeType",
        "bitrate",
        "width",
        "height",
        "initRange",
        "indexRange",
        "lastModified",
        "contentLength",
        "quality",
        "fps",
        "qualityLabel",
        "projectionType",
        "averageBitrate",
        "approxDurationMs",
        "highReplication",
        "audioQuality",
        "audioSampleRate",
        "audioChannels"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdaptiveFormat implements Serializable {

    @JsonProperty("itag")
    private Integer itag;
    @JsonProperty("url")
    private String url;
    @JsonProperty("mimeType")
    private String mimeType;
    @JsonProperty("bitrate")
    private Integer bitrate;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("initRange")
    private InitRange initRange;
    @JsonProperty("indexRange")
    private IndexRange indexRange;
    @JsonProperty("lastModified")
    private String lastModified;
    @JsonProperty("contentLength")
    private String contentLength;
    @JsonProperty("quality")
    private String quality;
    @JsonProperty("fps")
    private Integer fps;
    @JsonProperty("qualityLabel")
    private String qualityLabel;
    @JsonProperty("projectionType")
    private String projectionType;
    @JsonProperty("averageBitrate")
    private Integer averageBitrate;
    @JsonProperty("approxDurationMs")
    private String approxDurationMs;
    @JsonProperty("highReplication")
    private Boolean highReplication;
    @JsonProperty("audioQuality")
    private String audioQuality;
    @JsonProperty("audioSampleRate")
    private String audioSampleRate;
    @JsonProperty("audioChannels")
    private Integer audioChannels;
    public AdaptiveFormat() {
    }

    @JsonProperty("itag")
    public Integer getItag() {
        return itag;
    }

    @JsonProperty("itag")
    public void setItag(Integer itag) {
        this.itag = itag;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("mimeType")
    public String getMimeType() {
        return mimeType;
    }

    @JsonProperty("mimeType")
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @JsonProperty("bitrate")
    public Integer getBitrate() {
        return bitrate;
    }

    @JsonProperty("bitrate")
    public void setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
    }

    @JsonProperty("width")
    public Integer getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(Integer width) {
        this.width = width;
    }

    @JsonProperty("height")
    public Integer getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Integer height) {
        this.height = height;
    }

    @JsonProperty("initRange")
    public InitRange getInitRange() {
        return initRange;
    }

    @JsonProperty("initRange")
    public void setInitRange(InitRange initRange) {
        this.initRange = initRange;
    }

    @JsonProperty("indexRange")
    public IndexRange getIndexRange() {
        return indexRange;
    }

    @JsonProperty("indexRange")
    public void setIndexRange(IndexRange indexRange) {
        this.indexRange = indexRange;
    }

    @JsonProperty("lastModified")
    public String getLastModified() {
        return lastModified;
    }

    @JsonProperty("lastModified")
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    @JsonProperty("contentLength")
    public String getContentLength() {
        return contentLength;
    }

    @JsonProperty("contentLength")
    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }

    @JsonProperty("quality")
    public String getQuality() {
        return quality;
    }

    @JsonProperty("quality")
    public void setQuality(String quality) {
        this.quality = quality;
    }

    @JsonProperty("fps")
    public Integer getFps() {
        return fps;
    }

    @JsonProperty("fps")
    public void setFps(Integer fps) {
        this.fps = fps;
    }

    @JsonProperty("qualityLabel")
    public String getQualityLabel() {
        return qualityLabel;
    }

    @JsonProperty("qualityLabel")
    public void setQualityLabel(String qualityLabel) {
        this.qualityLabel = qualityLabel;
    }

    @JsonProperty("projectionType")
    public String getProjectionType() {
        return projectionType;
    }

    @JsonProperty("projectionType")
    public void setProjectionType(String projectionType) {
        this.projectionType = projectionType;
    }

    @JsonProperty("averageBitrate")
    public Integer getAverageBitrate() {
        return averageBitrate;
    }

    @JsonProperty("averageBitrate")
    public void setAverageBitrate(Integer averageBitrate) {
        this.averageBitrate = averageBitrate;
    }

    @JsonProperty("approxDurationMs")
    public String getApproxDurationMs() {
        return approxDurationMs;
    }

    @JsonProperty("approxDurationMs")
    public void setApproxDurationMs(String approxDurationMs) {
        this.approxDurationMs = approxDurationMs;
    }

    @JsonProperty("highReplication")
    public Boolean getHighReplication() {
        return highReplication;
    }

    @JsonProperty("highReplication")
    public void setHighReplication(Boolean highReplication) {
        this.highReplication = highReplication;
    }

    @JsonProperty("audioQuality")
    public String getAudioQuality() {
        return audioQuality;
    }

    @JsonProperty("audioQuality")
    public void setAudioQuality(String audioQuality) {
        this.audioQuality = audioQuality;
    }

    @JsonProperty("audioSampleRate")
    public String getAudioSampleRate() {
        return audioSampleRate;
    }

    @JsonProperty("audioSampleRate")
    public void setAudioSampleRate(String audioSampleRate) {
        this.audioSampleRate = audioSampleRate;
    }

    @JsonProperty("audioChannels")
    public Integer getAudioChannels() {
        return audioChannels;
    }

    @JsonProperty("audioChannels")
    public void setAudioChannels(Integer audioChannels) {
        this.audioChannels = audioChannels;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("itag", itag).append("url", url).append("mimeType", mimeType).append("bitrate", bitrate).append("width", width).append("height", height).append("initRange", initRange).append("indexRange", indexRange).append("lastModified", lastModified).append("contentLength", contentLength).append("quality", quality).append("fps", fps).append("qualityLabel", qualityLabel).append("projectionType", projectionType).append("averageBitrate", averageBitrate).append("approxDurationMs", approxDurationMs).append("highReplication", highReplication).append("audioQuality", audioQuality).append("audioSampleRate", audioSampleRate).append("audioChannels", audioChannels).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(itag).append(indexRange).append(fps).append(projectionType).append(initRange).append(bitrate).append(mimeType).append(audioQuality).append(approxDurationMs).append(url).append(audioSampleRate).append(quality).append(qualityLabel).append(audioChannels).append(width).append(highReplication).append(contentLength).append(lastModified).append(height).append(averageBitrate).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AdaptiveFormat) == false) {
            return false;
        }
        AdaptiveFormat rhs = ((AdaptiveFormat) other);
        return new EqualsBuilder().append(itag, rhs.itag).append(indexRange, rhs.indexRange).append(fps, rhs.fps).append(projectionType, rhs.projectionType).append(initRange, rhs.initRange).append(bitrate, rhs.bitrate).append(mimeType, rhs.mimeType).append(audioQuality, rhs.audioQuality).append(approxDurationMs, rhs.approxDurationMs).append(url, rhs.url).append(audioSampleRate, rhs.audioSampleRate).append(quality, rhs.quality).append(qualityLabel, rhs.qualityLabel).append(audioChannels, rhs.audioChannels).append(width, rhs.width).append(highReplication, rhs.highReplication).append(contentLength, rhs.contentLength).append(lastModified, rhs.lastModified).append(height, rhs.height).append(averageBitrate, rhs.averageBitrate).isEquals();
    }

}
