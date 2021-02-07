package com.botmasterzzz.controller.service;

import com.botmasterzzz.controller.dto.instagram.InstagramMediaDataDTO;
import com.botmasterzzz.controller.dto.soundcloud.SoundCloudDTO;
import com.botmasterzzz.controller.dto.youtube.YouTubeMediaDTO;
import com.proto.instagram.LoginResponse;

import java.util.List;

public interface ScrapperService {

    LoginResponse login(String code, String proxy);

    InstagramMediaDataDTO requestSomeMedia(String hashtag);

    InstagramMediaDataDTO requestSomeMedia(String hashtag, String nextMaxIdg);

    InstagramMediaDataDTO requestMediaFromCode(String code);

    YouTubeMediaDTO grabMediaMetaData(String videoIdentifier);

    List<YouTubeMediaDTO> searchYouTubeMedia(String queryTerm, long maxResults);

    SoundCloudDTO grabSoundMetaData(String soundCloudLink);

}
