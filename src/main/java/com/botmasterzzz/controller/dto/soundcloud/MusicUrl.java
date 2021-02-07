package com.botmasterzzz.controller.dto.soundcloud;

import com.google.common.base.Objects;

public class MusicUrl {

    private String musicUrl;

    public MusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicUrl musicUrl1 = (MusicUrl) o;
        return Objects.equal(musicUrl, musicUrl1.musicUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(musicUrl);
    }

    @Override
    public String toString() {
        return "MusicUrl{" +
                "musicUrl='" + musicUrl + '\'' +
                '}';
    }
}
