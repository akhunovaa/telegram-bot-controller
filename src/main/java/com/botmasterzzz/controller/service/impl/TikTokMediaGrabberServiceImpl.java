package com.botmasterzzz.controller.service.impl;

import com.botmasterzzz.controller.controller.tiktok.VideoUrl;
import com.botmasterzzz.controller.core.robot.Sender;
import com.botmasterzzz.controller.dto.ReceivedMediaFile;
import com.botmasterzzz.controller.dto.tiktok.Collector;
import com.botmasterzzz.controller.dto.tiktok.TikTokMediaDataDTO;
import com.botmasterzzz.controller.dto.tiktok.music.TikTokMusicDataDTO;
import com.botmasterzzz.controller.service.DatabaseService;
import com.botmasterzzz.controller.service.TikTokMediaGrabberService;
import com.botmasterzzz.controller.service.UserContextService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class TikTokMediaGrabberServiceImpl extends Sender implements TikTokMediaGrabberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TikTokMediaGrabberServiceImpl.class);

    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private UserContextService userContextService;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${video.file.upload.path}")
    private String path;
    @Value("${tiktok.scrapper.ip}")
    private String tikTokScrapperIp;
    @Value("${tiktok.scrapper.port}")
    private String tikTokScrapperPort;


    @Override
    public List<ReceivedMediaFile> downloadVideo(String tag, int count, Long chatId) {
        if (null == tag) {
            tag = "beautiful";
        }
        List<ReceivedMediaFile> uploadedFiles = new ArrayList<>();
        String scrapperHost = tikTokScrapperIp + ":" + tikTokScrapperPort;
        String fullScrapperUrl = "http://" + scrapperHost + "/" + "hashtag" + "/" + tag + "/" + count + "/" + "true";
        String response = execute(fullScrapperUrl);
        LOGGER.info("Received from TikTOK scrapper this data {}", response);
        TikTokMediaDataDTO tikTokMediaDataDTO = null;
        try {
            tikTokMediaDataDTO = objectMapper.readValue(response, TikTokMediaDataDTO.class);
        } catch (JsonProcessingException jsonProcessingException) {
            LOGGER.error("Error with json data read from TikTOK response with params", jsonProcessingException);
        } catch (IllegalArgumentException illegalArgumentException) {
            userContextService.setRemain(chatId, false);
            userContextService.setAnon(chatId, false);
            userContextService.setTagRemain(chatId, false);
            //redisUserContextService.setLoading(false);
            return Collections.emptyList();
        }
        if (tikTokMediaDataDTO != null) {
            List<File> videoFileList = new ArrayList<>();
            String userAgentHeader = null == tikTokMediaDataDTO.getHeaders().getUserAgent() ? (String) tikTokMediaDataDTO.getHeaders().getAdditionalProperties().get("user-agent") : tikTokMediaDataDTO.getHeaders().getUserAgent();
            String refererHeader = null == tikTokMediaDataDTO.getHeaders().getReferer() ? (String) tikTokMediaDataDTO.getHeaders().getAdditionalProperties().get("referer") : tikTokMediaDataDTO.getHeaders().getReferer();
            String cookieHeader = null == tikTokMediaDataDTO.getHeaders().getCookie() ? (String) tikTokMediaDataDTO.getHeaders().getAdditionalProperties().get("cookie") : tikTokMediaDataDTO.getHeaders().getCookie();
            tikTokMediaDataDTO.getCollector().parallelStream().forEach(item -> {
                String videoId = item.getId();
                String pathForMedia = path + "/" + videoId + ".mp4";
                File mediaFile = new File(pathForMedia);
                String videoUrl = item.getVideoUrl();
                String pathForCover = path + "/covers/" + videoId + ".jpg";
                File mediaCoverFile = new File(pathForCover);
                String coverUrl = item.getCovers().getDefault();
                //String withoutWatermarkVideoUrl = withoutWatermark(videoUrl, userAgentHeader, refererHeader, cookieHeader);
//                if (StringUtils.isEmpty(item.getVideoUrlNoWaterMark())) {
//                    item.setVideoUrlNoWaterMark(withoutWatermarkVideoUrl);
//                }
                if (mediaFile.exists()) {
                    long bytes = 0;
                    try {
                        bytes = Files.size(mediaFile.toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (bytes < 1000L) {
                        mediaFile.delete();
                    }
                }
                if (!mediaFile.exists()) {
                    executeMedia(videoUrl, mediaFile, userAgentHeader, refererHeader, cookieHeader);
                }
                if (!mediaCoverFile.exists()) {
                    executeMedia(coverUrl, mediaCoverFile, userAgentHeader, refererHeader, cookieHeader);
                }
            });

            for (Collector collectedItem : tikTokMediaDataDTO.getCollector()) {
                String videoId = collectedItem.getId();
                String text = collectedItem.getText();
                String authorName = collectedItem.getAuthorMeta().getName();
                String authorNickName = collectedItem.getAuthorMeta().getNickName();
                String coverUrl = collectedItem.getCovers().getOrigin();
                Integer videoWidth = collectedItem.getVideoMeta().getWidth();
                Integer videoHeight = collectedItem.getVideoMeta().getHeight();
                Integer duration = collectedItem.getVideoMeta().getDuration();
                Long createTime = Long.valueOf(collectedItem.getCreateTime());

                String pathForMedia = path + "/" + videoId + ".mp4";
                String pathForCover = path + "/" + "/covers/" + videoId + ".jpg";
                File mediaFile = new File(pathForMedia);
                ReceivedMediaFile receivedMediaFile = new ReceivedMediaFile();
                if (mediaFile.exists()) {
                    //TelegramUserMediaEntity telegramUserMediaEntity = telegramMediaService.telegramUserMediaGet(mediaFile.getAbsolutePath());
                    //String fileId = telegramUserMediaEntity.getFileId();
                    //receivedMediaFile.setId(telegramUserMediaEntity.getId());
//                    if (null != fileId){
//                        receivedMediaFile.setFileId(fileId);
//                    }else {
//                        receivedMediaFile.setFile(mediaFile);
//                    }
                    videoFileList.add(mediaFile);
                    receivedMediaFile.setFile(videoFileList);
                }
                receivedMediaFile.setId(videoId);
                receivedMediaFile.setTitle(text);
                receivedMediaFile.setDescription(text);
                receivedMediaFile.setDuration(Long.valueOf(duration));
                receivedMediaFile.setWidth(Long.valueOf(videoWidth));
                receivedMediaFile.setHeight(Long.valueOf(videoHeight));
                receivedMediaFile.setCoverUrl(coverUrl);
                receivedMediaFile.setCreateTime(createTime);
                receivedMediaFile.setAuthorName(authorName);
                receivedMediaFile.setAuthorNickName(authorNickName);
                receivedMediaFile.setVideoUrl(collectedItem.getVideoUrl());
                receivedMediaFile.setVideoWithoutWatermarkUrl(collectedItem.getVideoUrlNoWaterMark());
                receivedMediaFile.setMusicId(collectedItem.getMusicMeta().getMusicId());
                uploadedFiles.add(receivedMediaFile);
            }
        }
        uploadedFiles.parallelStream().forEach(databaseService::receivedMediaAdd);
        return uploadedFiles;
    }

    @Override
    public File downLoadThumbnail(String url, String fileName) {
        String pathForCover = path + "/covers/" + fileName + ".jpg";
        File thumbNailFile = new File(pathForCover);
        if (thumbNailFile.exists()) {
            return thumbNailFile;
        }
        return executeMedia(url, thumbNailFile);
    }

    @Override
    public File downloadVideo(String url, String fileName) {
        String pathForCover = path + "/" + fileName + ".mp4";
        File videoFile = new File(pathForCover);
        if (videoFile.exists()) {
            return videoFile;
        }
        return executeMedia(url, videoFile);
    }

    @Override
    public List<ReceivedMediaFile> downloadTrends(int count, Long chatId) {

        List<ReceivedMediaFile> uploadedFiles = new ArrayList<>();
        String scrapperHost = tikTokScrapperIp + ":" + tikTokScrapperPort;
        String fullScrapperUrl = "http://" + scrapperHost + "/" + "trend" + "/" + count;
        String response = execute(fullScrapperUrl);
        LOGGER.info("Received from TikTOK scrapper this data {}", response);
        TikTokMediaDataDTO tikTokMediaDataDTO = null;
        try {
            tikTokMediaDataDTO = objectMapper.readValue(response, TikTokMediaDataDTO.class);
        } catch (JsonProcessingException jsonProcessingException) {
            LOGGER.error("Error with json data read from TikTOK response with params", jsonProcessingException);
        } catch (IllegalArgumentException illegalArgumentException) {
            userContextService.setRemain(chatId, false);
            userContextService.setAnon(chatId, false);
            userContextService.setTagRemain(chatId, false);
            //redisUserContextService.setLoading(false);
            return Collections.emptyList();
        }
        if (tikTokMediaDataDTO != null) {
            List<File> videoFileList = new ArrayList<>();
            String userAgentHeader = null == tikTokMediaDataDTO.getHeaders().getUserAgent() ? (String) tikTokMediaDataDTO.getHeaders().getAdditionalProperties().get("user-agent") : tikTokMediaDataDTO.getHeaders().getUserAgent();
            String refererHeader = null == tikTokMediaDataDTO.getHeaders().getReferer() ? (String) tikTokMediaDataDTO.getHeaders().getAdditionalProperties().get("referer") : tikTokMediaDataDTO.getHeaders().getReferer();
            String cookieHeader = null == tikTokMediaDataDTO.getHeaders().getCookie() ? (String) tikTokMediaDataDTO.getHeaders().getAdditionalProperties().get("cookie") : tikTokMediaDataDTO.getHeaders().getCookie();
            tikTokMediaDataDTO.getCollector().parallelStream().forEach(item -> {
                String videoId = item.getId();
                String pathForMedia = path + "/" + videoId + ".mp4";
                File mediaFile = new File(pathForMedia);
                String videoUrl = item.getVideoUrl();
                String pathForCover = path + "/covers/" + videoId + ".jpg";
                File mediaCoverFile = new File(pathForCover);
                String coverUrl = item.getCovers().getDefault();
                //String withoutWatermarkVideoUrl = withoutWatermark(videoUrl, userAgentHeader, refererHeader, cookieHeader);
//                if (StringUtils.isEmpty(item.getVideoUrlNoWaterMark())) {
//                    item.setVideoUrlNoWaterMark(withoutWatermarkVideoUrl);
//                }
                if (mediaFile.exists()) {
                    long bytes = 0;
                    try {
                        bytes = Files.size(mediaFile.toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (bytes < 1000L) {
                        mediaFile.delete();
                    }
                }
                if (!mediaFile.exists()) {
                    executeMedia(videoUrl, mediaFile, userAgentHeader, refererHeader, cookieHeader);
                }
                if (!mediaCoverFile.exists()) {
                    executeMedia(coverUrl, mediaCoverFile, userAgentHeader, refererHeader, cookieHeader);
                }
            });

            for (Collector collectedItem : tikTokMediaDataDTO.getCollector()) {
                String videoId = collectedItem.getId();
                String text = collectedItem.getText();
                String authorName = collectedItem.getAuthorMeta().getName();
                String authorNickName = collectedItem.getAuthorMeta().getNickName();
                String coverUrl = collectedItem.getCovers().getOrigin();
                Integer videoWidth = collectedItem.getVideoMeta().getWidth();
                Integer videoHeight = collectedItem.getVideoMeta().getHeight();
                Integer duration = collectedItem.getVideoMeta().getDuration();
                Long createTime = Long.valueOf(collectedItem.getCreateTime());

                String pathForMedia = path + "/" + videoId + ".mp4";
                String pathForCover = path + "/" + "/covers/" + videoId + ".jpg";
                File mediaFile = new File(pathForMedia);
                ReceivedMediaFile receivedMediaFile = new ReceivedMediaFile();
                if (mediaFile.exists()) {
                    //TelegramUserMediaEntity telegramUserMediaEntity = telegramMediaService.telegramUserMediaGet(mediaFile.getAbsolutePath());
                    //String fileId = telegramUserMediaEntity.getFileId();
                    //receivedMediaFile.setId(telegramUserMediaEntity.getId());
//                    if (null != fileId){
//                        receivedMediaFile.setFileId(fileId);
//                    }else {
//                        receivedMediaFile.setFile(mediaFile);
//                    }
                    videoFileList.add(mediaFile);
                    receivedMediaFile.setFile(videoFileList);
                }
                receivedMediaFile.setId(videoId);
                receivedMediaFile.setTitle(text);
                receivedMediaFile.setDescription(text);
                receivedMediaFile.setDuration(Long.valueOf(duration));
                receivedMediaFile.setWidth(Long.valueOf(videoWidth));
                receivedMediaFile.setHeight(Long.valueOf(videoHeight));
                receivedMediaFile.setCoverUrl(coverUrl);
                receivedMediaFile.setCreateTime(createTime);
                receivedMediaFile.setAuthorName(authorName);
                receivedMediaFile.setAuthorNickName(authorNickName);
                receivedMediaFile.setVideoUrl(collectedItem.getVideoUrl());
                receivedMediaFile.setVideoWithoutWatermarkUrl(collectedItem.getVideoUrlNoWaterMark());
                receivedMediaFile.setMusicId(collectedItem.getMusicMeta().getMusicId());
                uploadedFiles.add(receivedMediaFile);
            }
        }
        uploadedFiles.parallelStream().forEach(databaseService::receivedMediaAdd);
        return uploadedFiles;
    }

    @Override
    public List<ReceivedMediaFile> downloadWatermarkFreeVideo(String tiktokVideoIdentifier, Long chatId) {

        List<ReceivedMediaFile> uploadedFiles = new ArrayList<>();
        String scrapperHost = tikTokScrapperIp + ":" + tikTokScrapperPort;
        String fullScrapperUrl = "http://" + scrapperHost + "/" + "video" + "/" + tiktokVideoIdentifier;
        String response = execute(fullScrapperUrl);
        LOGGER.info("Received from TikTOK scrapper this data {}", response);
        TikTokMediaDataDTO tikTokMediaDataDTO = null;
        try {
            tikTokMediaDataDTO = objectMapper.readValue(response, TikTokMediaDataDTO.class);
        } catch (JsonProcessingException jsonProcessingException) {
            LOGGER.error("Error with json data read from TikTOK response with params", jsonProcessingException);
        } catch (IllegalArgumentException illegalArgumentException) {
            userContextService.setRemain(chatId, false);
            userContextService.setAnon(chatId, false);
            userContextService.setTagRemain(chatId, false);
            //redisUserContextService.setLoading(false);
            return Collections.emptyList();
        }
        if (tikTokMediaDataDTO != null) {
            List<File> videoFileList = new ArrayList<>();
            String userAgentHeader = null == tikTokMediaDataDTO.getHeaders().getUserAgent() ? (String) tikTokMediaDataDTO.getHeaders().getAdditionalProperties().get("user-agent") : tikTokMediaDataDTO.getHeaders().getUserAgent();
            String refererHeader = null == tikTokMediaDataDTO.getHeaders().getReferer() ? (String) tikTokMediaDataDTO.getHeaders().getAdditionalProperties().get("referer") : tikTokMediaDataDTO.getHeaders().getReferer();
            String cookieHeader = null == tikTokMediaDataDTO.getHeaders().getCookie() ? (String) tikTokMediaDataDTO.getHeaders().getAdditionalProperties().get("cookie") : tikTokMediaDataDTO.getHeaders().getCookie();
            tikTokMediaDataDTO.getCollector().parallelStream().forEach(item -> {
                String videoId = item.getId();
                String pathForMedia = path + "/" + videoId + ".mp4";
                File mediaFile = new File(pathForMedia);
                boolean deleted;
                String videoUrl = StringUtils.isEmpty(item.getVideoUrlNoWaterMark()) ? item.getVideoUrl() : item.getVideoUrlNoWaterMark();
                String withoutWatermarkVideoUrl = withoutWatermark(mediaFile);
                if (StringUtils.isEmpty(withoutWatermarkVideoUrl)) {
                    withoutWatermarkVideoUrl = withoutWatermark(videoUrl, userAgentHeader, refererHeader, cookieHeader);
                }
                if (StringUtils.isEmpty(item.getVideoUrlNoWaterMark())) {
                    item.setVideoUrlNoWaterMark(withoutWatermarkVideoUrl);
                }
                if (!StringUtils.isEmpty(withoutWatermarkVideoUrl)) {
                    deleted = mediaFile.delete();
                    if (deleted) {
                        executeWatermarkMedia(withoutWatermarkVideoUrl, mediaFile, userAgentHeader, cookieHeader);
                    }
                }
            });

            for (Collector collectedItem : tikTokMediaDataDTO.getCollector()) {
                String videoId = collectedItem.getId();
                String text = collectedItem.getText();
                String authorName = collectedItem.getAuthorMeta().getName();
                String authorNickName = collectedItem.getAuthorMeta().getNickName();
                String coverUrl = collectedItem.getCovers().getOrigin();
                Integer videoWidth = collectedItem.getVideoMeta().getWidth();
                Integer videoHeight = collectedItem.getVideoMeta().getHeight();
                Integer duration = collectedItem.getVideoMeta().getDuration();
                Long createTime = Long.valueOf(collectedItem.getCreateTime());

                String pathForMedia = path + "/" + videoId + ".mp4";
                File mediaFile = new File(pathForMedia);
                ReceivedMediaFile receivedMediaFile = new ReceivedMediaFile();
                if (mediaFile.exists()) {
                    videoFileList.add(mediaFile);
                    receivedMediaFile.setFile(videoFileList);
                }
                receivedMediaFile.setId(videoId);
                receivedMediaFile.setTitle(text);
                receivedMediaFile.setDescription(text);
                receivedMediaFile.setDuration(Long.valueOf(duration));
                receivedMediaFile.setWidth(Long.valueOf(videoWidth));
                receivedMediaFile.setHeight(Long.valueOf(videoHeight));
                receivedMediaFile.setCoverUrl(coverUrl);
                receivedMediaFile.setCreateTime(createTime);
                receivedMediaFile.setAuthorName(authorName);
                receivedMediaFile.setAuthorNickName(authorNickName);
                receivedMediaFile.setVideoUrl(collectedItem.getVideoUrl());
                receivedMediaFile.setVideoWithoutWatermarkUrl(collectedItem.getVideoUrlNoWaterMark());
                receivedMediaFile.setMusicId(collectedItem.getMusicMeta().getMusicId());
                uploadedFiles.add(receivedMediaFile);
            }
        }
        return uploadedFiles;
    }

    @Override
    public List<ReceivedMediaFile> downloadMusicFromVideo(String tiktokMusicIdentifier, Long chatId) {

        List<ReceivedMediaFile> uploadedFiles = new ArrayList<>();
        String scrapperHost = tikTokScrapperIp + ":" + tikTokScrapperPort;
        String fullScrapperUrl = "http://" + scrapperHost + "/" + "music" + "/" + tiktokMusicIdentifier;
        String response = execute(fullScrapperUrl);
        LOGGER.info("Received from TikTOK scrapper this data {}", response);
        TikTokMusicDataDTO tikTokMusicDataDTO = null;
        try {
            tikTokMusicDataDTO = objectMapper.readValue(response, TikTokMusicDataDTO.class);
        } catch (JsonProcessingException jsonProcessingException) {
            LOGGER.error("Error with json data read from TikTOK response with params", jsonProcessingException);
        } catch (IllegalArgumentException illegalArgumentException) {
            userContextService.setRemain(chatId, false);
            userContextService.setTagRemain(chatId, false);
            return Collections.emptyList();
        }
        if (tikTokMusicDataDTO != null) {
            List<File> musicFileList = new ArrayList<>();
            String musicId = tikTokMusicDataDTO.getMusic().getId();
            String keyToken = null != tikTokMusicDataDTO.getMusic() ? tikTokMusicDataDTO.getMusic().getKeyToken() : "_";
            String playToken = null != tikTokMusicDataDTO.getMusic() ? tikTokMusicDataDTO.getMusic().getPlayToken() : "_";
            String authorName = null != tikTokMusicDataDTO.getMusic() ? tikTokMusicDataDTO.getMusic().getAuthorName() : "_";
            String musicTitle = null != tikTokMusicDataDTO.getMusic() ? tikTokMusicDataDTO.getMusic().getTitle() : "_";
            String coverThumbUrl = null != tikTokMusicDataDTO.getMusic() ? tikTokMusicDataDTO.getMusic().getCoverThumb() : "_";
            String description = null != tikTokMusicDataDTO.getShareMeta() ? tikTokMusicDataDTO.getShareMeta().getTitle() : "_";
            String playUrl = null != tikTokMusicDataDTO.getMusic() ? tikTokMusicDataDTO.getMusic().getPlayUrl() : "_";
            int duration = null != tikTokMusicDataDTO.getMusic() ? tikTokMusicDataDTO.getMusic().getDuration() : 0;

            String pathForMedia = path + "/" + musicId + ".mp3";
            String pathForMediaCover = path + "/" + "covers/" + musicId + ".jpg";
            File mediaFile = new File(pathForMedia);
            File mediaCoverFile = new File(pathForMediaCover);

            if (!mediaFile.exists() && !StringUtils.isEmpty(playUrl)) {
                executeMedia(playUrl, mediaFile);
            }
            if (!mediaCoverFile.exists() && !StringUtils.isEmpty(coverThumbUrl)) {
                executeMedia(coverThumbUrl, mediaCoverFile);
            }
            ReceivedMediaFile receivedMediaFile = new ReceivedMediaFile();
            if (mediaFile.exists()) {
                musicFileList.add(mediaFile);
                receivedMediaFile.setFile(musicFileList);
            }
            receivedMediaFile.setId(musicId);
            receivedMediaFile.setFileId(pathForMedia);
            receivedMediaFile.setAuthorName(authorName);
            receivedMediaFile.setTitle(musicTitle);
            receivedMediaFile.setDescription(description);
            receivedMediaFile.setDuration(Long.valueOf(duration));
            receivedMediaFile.setCoverThumb(pathForMediaCover);
            receivedMediaFile.setCoverUrl(coverThumbUrl);
            receivedMediaFile.setVideoUrl(playUrl);
            uploadedFiles.add(receivedMediaFile);
        }
        return uploadedFiles;
    }

    @Override
    public List<ReceivedMediaFile> downloadVideoFromLink(String url, Long chatId) {
        List<ReceivedMediaFile> uploadedFiles = new ArrayList<>();
        String scrapperHost = tikTokScrapperIp + ":" + tikTokScrapperPort;
        String fullScrapperUrl = "http://" + scrapperHost + "/" + "video";
        String response = execute(fullScrapperUrl, new Gson().toJson(new VideoUrl(url)));
        TikTokMediaDataDTO tikTokMediaDataDTO = null;
        try {
            tikTokMediaDataDTO = objectMapper.readValue(response, TikTokMediaDataDTO.class);
        } catch (JsonProcessingException jsonProcessingException) {
            LOGGER.error("Error with json data read from TikTOK response with params", jsonProcessingException);
        } catch (IllegalArgumentException illegalArgumentException) {
            userContextService.setRemain(chatId, false);
            userContextService.setAnon(chatId, false);
            userContextService.setTagRemain(chatId, false);
            //redisUserContextService.setLoading(false);
            return Collections.emptyList();
        }
        if (tikTokMediaDataDTO != null) {
            List<File> videoFileList = new ArrayList<>();
            String userAgentHeader = null == tikTokMediaDataDTO.getHeaders().getUserAgent() ? (String) tikTokMediaDataDTO.getHeaders().getAdditionalProperties().get("user-agent") : tikTokMediaDataDTO.getHeaders().getUserAgent();
            String refererHeader = null == tikTokMediaDataDTO.getHeaders().getReferer() ? (String) tikTokMediaDataDTO.getHeaders().getAdditionalProperties().get("referer") : tikTokMediaDataDTO.getHeaders().getReferer();
            String cookieHeader = null == tikTokMediaDataDTO.getHeaders().getCookie() ? (String) tikTokMediaDataDTO.getHeaders().getAdditionalProperties().get("cookie") : tikTokMediaDataDTO.getHeaders().getCookie();
            tikTokMediaDataDTO.getCollector().parallelStream().forEach(item -> {
                String videoId = item.getId();
                String pathForMedia = path + "/" + videoId + ".mp4";
                File mediaFile = new File(pathForMedia);
                if (mediaFile.exists()) {
                    long bytes = 0;
                    try {
                        bytes = Files.size(mediaFile.toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (bytes < 1000L) {
                        mediaFile.delete();
                    }
                }
                String videoUrl = item.getVideoUrl();
                String pathForCover = path + "/covers/" + videoId + ".jpg";
                File mediaCoverFile = new File(pathForCover);
                String coverUrl = item.getCovers().getDefault();
                if (!mediaFile.exists()) {
                    executeMedia(videoUrl, mediaFile, userAgentHeader, refererHeader, cookieHeader);
                }
                if (!mediaCoverFile.exists()) {
                    executeMedia(coverUrl, mediaCoverFile, userAgentHeader, refererHeader, cookieHeader);
                }
            });

            for (Collector collectedItem : tikTokMediaDataDTO.getCollector()) {
                String videoId = collectedItem.getId();
                String text = collectedItem.getText();
                String authorName = collectedItem.getAuthorMeta().getName();
                String authorNickName = collectedItem.getAuthorMeta().getNickName();
                String coverUrl = collectedItem.getCovers().getOrigin();
                Integer videoWidth = collectedItem.getVideoMeta().getWidth();
                Integer videoHeight = collectedItem.getVideoMeta().getHeight();
                Integer duration = collectedItem.getVideoMeta().getDuration();
                Long createTime = Long.valueOf(collectedItem.getCreateTime());

                String pathForMedia = path + "/" + videoId + ".mp4";
                String pathForCover = path + "/" + "/covers/" + videoId + ".jpg";
                File mediaFile = new File(pathForMedia);
                ReceivedMediaFile receivedMediaFile = new ReceivedMediaFile();
                if (mediaFile.exists()) {
                    videoFileList.add(mediaFile);
                    receivedMediaFile.setFile(videoFileList);
                }
                receivedMediaFile.setId(videoId);
                receivedMediaFile.setTitle(text);
                receivedMediaFile.setDescription(text);
                receivedMediaFile.setDuration(Long.valueOf(duration));
                receivedMediaFile.setWidth(Long.valueOf(videoWidth));
                receivedMediaFile.setHeight(Long.valueOf(videoHeight));
                receivedMediaFile.setCoverUrl(coverUrl);
                receivedMediaFile.setCreateTime(createTime);
                receivedMediaFile.setAuthorName(authorName);
                receivedMediaFile.setAuthorNickName(authorNickName);
                receivedMediaFile.setVideoUrl(collectedItem.getVideoUrl());
                receivedMediaFile.setVideoWithoutWatermarkUrl(collectedItem.getVideoUrlNoWaterMark());
                receivedMediaFile.setMusicId(collectedItem.getMusicMeta().getMusicId());
                uploadedFiles.add(receivedMediaFile);
            }
        }
        uploadedFiles.parallelStream().forEach(databaseService::receivedMediaAdd);
        return uploadedFiles;
    }

    @Override
    public List<ReceivedMediaFile> getVideoList(String tag, int count, int minCursor, int maxCursor) {
        if (null == tag) {
            tag = "beautiful";
        }
        List<ReceivedMediaFile> uploadedFiles = new ArrayList<>();
        String scrapperHost = tikTokScrapperIp + ":" + tikTokScrapperPort;
        String fullScrapperUrl = "http://" + scrapperHost + "/" + "hashtag" + "/" + tag + "/" + count + "/" + "true";
        String response = execute(fullScrapperUrl);
        LOGGER.info("Received from TikTOK scrapper this data {}", response);
        TikTokMediaDataDTO tikTokMediaDataDTO = null;
        try {
            tikTokMediaDataDTO = objectMapper.readValue(response, TikTokMediaDataDTO.class);
        } catch (JsonProcessingException jsonProcessingException) {
            LOGGER.error("Error with json data read from TikTOK response with params", jsonProcessingException);
        } catch (IllegalArgumentException illegalArgumentException) {
            return Collections.emptyList();
        }
        if (tikTokMediaDataDTO != null) {
            List<File> videoFileList = new ArrayList<>();
            String userAgentHeader = null == tikTokMediaDataDTO.getHeaders().getUserAgent() ? (String) tikTokMediaDataDTO.getHeaders().getAdditionalProperties().get("user-agent") : tikTokMediaDataDTO.getHeaders().getUserAgent();
            String refererHeader = null == tikTokMediaDataDTO.getHeaders().getReferer() ? (String) tikTokMediaDataDTO.getHeaders().getAdditionalProperties().get("referer") : tikTokMediaDataDTO.getHeaders().getReferer();
            String cookieHeader = null == tikTokMediaDataDTO.getHeaders().getCookie() ? (String) tikTokMediaDataDTO.getHeaders().getAdditionalProperties().get("cookie") : tikTokMediaDataDTO.getHeaders().getCookie();
            tikTokMediaDataDTO.getCollector().parallelStream().forEach(item -> {
                String videoId = item.getId();
                String pathForMedia = path + "/" + videoId + ".mp4";
                File mediaFile = new File(pathForMedia);
                String videoUrl = item.getVideoUrl();
                String pathForCover = path + "/covers/" + videoId + ".jpg";
                File mediaCoverFile = new File(pathForCover);
                String coverUrl = item.getCovers().getDefault();
                if (!mediaFile.exists()) {
                    executeMedia(videoUrl, mediaFile, userAgentHeader, refererHeader, cookieHeader);
                }
                if (!mediaCoverFile.exists()) {
                    executeMedia(coverUrl, mediaCoverFile, userAgentHeader, refererHeader, cookieHeader);
                }
            });

            for (Collector collectedItem : tikTokMediaDataDTO.getCollector()) {
                String videoId = collectedItem.getId();
                String text = collectedItem.getText();
                String authorName = collectedItem.getAuthorMeta().getName();
                String authorNickName = collectedItem.getAuthorMeta().getNickName();
                String coverUrl = collectedItem.getCovers().getOrigin();
                Integer videoWidth = collectedItem.getVideoMeta().getWidth();
                Integer videoHeight = collectedItem.getVideoMeta().getHeight();
                Integer duration = collectedItem.getVideoMeta().getDuration();
                Long createTime = Long.valueOf(collectedItem.getCreateTime());

                String pathForMedia = path + "/" + videoId + ".mp4";
                File mediaFile = new File(pathForMedia);
                ReceivedMediaFile receivedMediaFile = new ReceivedMediaFile();
                if (mediaFile.exists()) {
                    videoFileList.add(mediaFile);
                    receivedMediaFile.setFile(videoFileList);
                }
                receivedMediaFile.setId(videoId);
                receivedMediaFile.setTitle(text);
                receivedMediaFile.setDescription(text);
                receivedMediaFile.setDuration(Long.valueOf(duration));
                receivedMediaFile.setWidth(Long.valueOf(videoWidth));
                receivedMediaFile.setHeight(Long.valueOf(videoHeight));
                receivedMediaFile.setCoverUrl(coverUrl);
                receivedMediaFile.setCreateTime(createTime);
                receivedMediaFile.setAuthorName(authorName);
                receivedMediaFile.setAuthorNickName(authorNickName);
                receivedMediaFile.setVideoUrl(collectedItem.getVideoUrl());
                receivedMediaFile.setVideoWithoutWatermarkUrl(collectedItem.getVideoUrlNoWaterMark());
                receivedMediaFile.setMusicId(collectedItem.getMusicMeta().getMusicId());
                uploadedFiles.add(receivedMediaFile);
            }
        }
        uploadedFiles.parallelStream().forEach(databaseService::receivedMediaAdd);
        return uploadedFiles;
    }

    public String withoutWatermark(final String url, String userAgent, String refererHeader, String cookie) {
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setRequestProperty("User-Agent", userAgent);
            httpURLConnection.setRequestProperty("Referer", refererHeader);
            httpURLConnection.setRequestProperty("Cookie", cookie);

            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                final StringBuilder stringBuffer = new StringBuilder();

                String readLine;
                while ((readLine = bufferedReader.readLine()) != null) {
                    stringBuffer.append(readLine);

                    if (stringBuffer.toString().contains("vid:")) {
                        try {
                            if (stringBuffer.substring(stringBuffer.indexOf("vid:")).startsWith("vid:")) {
                                final String substring = stringBuffer.substring(stringBuffer.indexOf("vid:"));
                                final String trim = substring.substring(4, substring.indexOf("%"))
                                        .replaceAll("[^A-Za-z0-9]", "").trim();
                                //return "https://api2-16-h2.musical.ly/aweme/v1/play/?video_id=" + trim;
                                //return "https://api2.musical.ly/aweme/v1/playwm/?video_id=" + trim;
                                //return "https://api2-16-h2.musical.ly/aweme/v1/play/?video_id=" + trim;
                                return "https://api2-16-h2.musical.ly/aweme/v1/play/?video_id=" + trim + "&vr_type=0&is_play_url=1&source=PackSourceEnum_PUBLISH&media_type=4&ratio=default&improve_bitrate=1";
                            }
                        } catch (final Exception e) {

                        }
                    }
                }
            }

        } catch (final Exception e) {

        }

        return "";
    }

    public String withoutWatermark(final File file) {
        try {

            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            final StringBuilder stringBuffer = new StringBuilder();

            String readLine;
            while ((readLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(readLine);

                if (stringBuffer.toString().contains("vid:")) {
                    try {
                        if (stringBuffer.substring(stringBuffer.indexOf("vid:")).startsWith("vid:")) {
                            final String substring = stringBuffer.substring(stringBuffer.indexOf("vid:"));
                            final String trim = substring.substring(4, substring.indexOf("%"))
                                    .replaceAll("[^A-Za-z0-9]", "").trim();
                            //return "https://api2-16-h2.musical.ly/aweme/v1/play/?video_id=" + trim;
                            //return "https://api2.musical.ly/aweme/v1/playwm/?video_id=" + trim;
                            //return "https://api2-16-h2.musical.ly/aweme/v1/play/?video_id=" + trim;
                            return "https://api2-16-h2.musical.ly/aweme/v1/play/?video_id=" + trim + "&vr_type=0&is_play_url=1&source=PackSourceEnum_PUBLISH&media_type=4&ratio=default&improve_bitrate=1";
                        }
                    } catch (final Exception e) {

                    }
                }
            }

        } catch (final Exception e) {

        }

        return "";
    }
}
