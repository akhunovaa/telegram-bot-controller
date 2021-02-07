package com.botmasterzzz.controller.service.impl;

import com.botmasterzzz.controller.core.robot.Sender;
import com.botmasterzzz.controller.dto.instagram.InstagramMediaDataDTO;
import com.botmasterzzz.controller.dto.soundcloud.MusicUrl;
import com.botmasterzzz.controller.dto.soundcloud.SoundCloudDTO;
import com.botmasterzzz.controller.dto.youtube.YouTubeMediaDTO;
import com.botmasterzzz.controller.service.ScrapperService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.proto.instagram.LoginResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service("soundCloud")
public class SoundCloudScrapperServiceImpl extends Sender implements ScrapperService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SoundCloudScrapperServiceImpl.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${sound.file.upload.path}")
    private String path;
    @Value("${sound.scrapper.ip}")
    private String soundCloudScrapperIp;
    @Value("${sound.scrapper.port}")
    private String soundCloudScrapperPort;

    @Override
    public SoundCloudDTO grabSoundMetaData(String soundCloudLink) {
        String scrapperHost = soundCloudScrapperIp + ":" + soundCloudScrapperPort;
        String fullScrapperUrl = "http://" + scrapperHost + "/" + "sound" + "/" + "download";
        String response = execute(fullScrapperUrl, new Gson().toJson(new MusicUrl(soundCloudLink)));
        LOGGER.info("Received from TikTOK scrapper this data {}", response);
        SoundCloudDTO soundCloudDTO = null;
        try {
            soundCloudDTO = objectMapper.readValue(response, SoundCloudDTO.class);
        } catch (JsonProcessingException jsonProcessingException) {
            LOGGER.error("Error with json data read from SoundCloud response with params", jsonProcessingException);
        } catch (IllegalArgumentException illegalArgumentException) {
            return new SoundCloudDTO();
        }
        String musicIdentifier = soundCloudDTO != null ? soundCloudDTO.getId() : "NN";
        String musicThumbnailUrl = soundCloudDTO != null ? soundCloudDTO.getThumbnail() : "NN";
        String pathForMediaThumbnail = path + "/" + "covers/" + musicIdentifier + ".jpg";
        File mediaThumbnailFile = new File(pathForMediaThumbnail);

        if (!mediaThumbnailFile.exists() && !StringUtils.isEmpty(pathForMediaThumbnail)) {
            executeMedia(musicThumbnailUrl, mediaThumbnailFile);
        }
        return soundCloudDTO;
    }


    @Override
    public LoginResponse login(String code, String proxy) {
        throw new RuntimeException("Not implemented here");
    }

    @Override
    public InstagramMediaDataDTO requestSomeMedia(String hashtag) {
        throw new RuntimeException("Not implemented here");
    }

    @Override
    public InstagramMediaDataDTO requestSomeMedia(String hashtag, String nextMaxIdg) {
        throw new RuntimeException("Not implemented here");
    }

    @Override
    public InstagramMediaDataDTO requestMediaFromCode(String code) {
        throw new RuntimeException("Not implemented here");
    }

    @Override
    public YouTubeMediaDTO grabMediaMetaData(String videoIdentifier) {
        throw new RuntimeException("Not implemented here");
    }

    @Override
    public List<YouTubeMediaDTO> searchYouTubeMedia(String queryTerm, long maxResults) {
        throw new RuntimeException("Not implemented here");
    }

}
