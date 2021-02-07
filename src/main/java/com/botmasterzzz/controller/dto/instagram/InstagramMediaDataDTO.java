package com.botmasterzzz.controller.dto.instagram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InstagramMediaDataDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;
    private String nextMaxId;
    private int statusCode;
    private int numResults;
    private boolean moreAvailable;
    private List<InstagramMediaItem> instagramMediaItemList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNextMaxId() {
        return nextMaxId;
    }

    public void setNextMaxId(String nextMaxId) {
        this.nextMaxId = nextMaxId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getNumResults() {
        return numResults;
    }

    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    public boolean isMoreAvailable() {
        return moreAvailable;
    }

    public void setMoreAvailable(boolean moreAvailable) {
        this.moreAvailable = moreAvailable;
    }

    public List<InstagramMediaItem> getInstagramMediaItemList() {
        return instagramMediaItemList;
    }

    public void setInstagramMediaItemList(List<InstagramMediaItem> instagramMediaItemList) {
        this.instagramMediaItemList = instagramMediaItemList;
    }

    @Override
    public String toString() {
        return "InstagramMediaDataDTO{" +
                "status='" + status + '\'' +
                ", nextMaxId='" + nextMaxId + '\'' +
                ", statusCode=" + statusCode +
                ", numResults=" + numResults +
                ", moreAvailable=" + moreAvailable +
                ", instagramMediaItemList=" + instagramMediaItemList +
                '}';
    }
}
