package com.botmasterzzz.controller.service.impl;

import com.botmasterzzz.controller.dto.instagram.*;
import com.botmasterzzz.controller.dto.soundcloud.SoundCloudDTO;
import com.botmasterzzz.controller.dto.youtube.YouTubeMediaDTO;
import com.botmasterzzz.controller.service.ScrapperService;
import com.proto.instagram.*;
import io.grpc.ManagedChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("instagram")
public class InstagramScrapperServiceImpl implements ScrapperService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstagramScrapperServiceImpl.class);

    private final ManagedChannel managedInstagramLoginChannel;

    private final ManagedChannel managedInstagramMediaChannel;

    private LoginServiceGrpc.LoginServiceBlockingStub loginServiceBlockingStub;

    private MediaServiceGrpc.MediaServiceBlockingStub mediaServiceBlockingStub;

    public InstagramScrapperServiceImpl(ManagedChannel managedInstagramLoginChannel, ManagedChannel managedInstagramMediaChannel) {
        this.managedInstagramLoginChannel = managedInstagramLoginChannel;
        this.managedInstagramMediaChannel = managedInstagramMediaChannel;
    }

    @PostConstruct
    private void initializeClient() {
        loginServiceBlockingStub = LoginServiceGrpc.newBlockingStub(managedInstagramLoginChannel);
        mediaServiceBlockingStub = MediaServiceGrpc.newBlockingStub(managedInstagramMediaChannel);
    }

    @Override
    public LoginResponse login(String code, String proxy) {
        LOGGER.info("Request to Instagram scrapper service => invoking login method");
        LoginRequest loginRequest = LoginRequest.newBuilder().setCode(code).setProxy(proxy)
                .build();
        LoginResponse loginResponse = loginServiceBlockingStub.withDeadlineAfter(2, TimeUnit.MINUTES).simpleLogin(loginRequest);
        LOGGER.info("LoginResponse from Instagram scrapper service => {}", loginResponse.toString());
        return loginResponse;
    }

    @Override
    @Cacheable(value = "instagram-hashtag-media-list", key = "#hashtag")
    public InstagramMediaDataDTO requestSomeMedia(String hashtag) {
        LOGGER.info("Request to Instagram scrapper service => requesting some media from service");

        MediaRequest mediaRequest = MediaRequest
                .newBuilder()
                .setHashtag(hashtag)
                .build();

        LOGGER.info("Request to Instagram scrapper service => requesting {}", mediaRequest.toString());
        MediaResponse mediaResponse = mediaServiceBlockingStub.withDeadlineAfter(2, TimeUnit.MINUTES).mediaListRequest(mediaRequest);
        LOGGER.info("Request to Instagram scrapper service => response {}", mediaResponse.toString());
        return instagramDtoBuilder(mediaResponse);
    }

    @Override
    @Cacheable(value = "instagram-hashtag-media-list", key = "#hashtag")
    public InstagramMediaDataDTO requestSomeMedia(String hashtag, String nextMaxId) {
        LOGGER.info("Request to Instagram scrapper service => requesting some media from service");

        MediaRequest mediaRequest = MediaRequest
                .newBuilder()
                .setHashtag(hashtag)
                .setNextMaxId(nextMaxId)
                .build();

        LOGGER.info("Request to Instagram scrapper service => requesting {}", mediaRequest.toString());
        MediaResponse mediaResponse = mediaServiceBlockingStub.withDeadlineAfter(2, TimeUnit.MINUTES).mediaListRequest(mediaRequest);
        LOGGER.info("Request to Instagram scrapper service => response {}", mediaResponse.toString());
        return instagramDtoBuilder(mediaResponse);
    }

    @Override
    @Cacheable(value = "instagram-code-media", key = "#code")
    public InstagramMediaDataDTO requestMediaFromCode(String code) {
        LOGGER.info("Request to Instagram scrapper service => requesting some media from service");

        MediaCodeRequest mediaCodeRequest = MediaCodeRequest
                .newBuilder()
                .setCode(code)
                .setPk(0L)
                .build();

        MediaResponse mediaResponse = mediaServiceBlockingStub.withDeadlineAfter(2, TimeUnit.MINUTES).mediaListRequestFromCode(mediaCodeRequest);
        LOGGER.info("Request to Instagram scrapper service => response {}", mediaResponse.toString());
        return instagramCodeDtoBuilder(mediaResponse);
    }

    @Override
    public YouTubeMediaDTO grabMediaMetaData(String videoIdentifier) {
        throw new RuntimeException("Not implemented here");
    }

    @Override
    public List<YouTubeMediaDTO> searchYouTubeMedia(String queryTerm, long maxResults) {
        throw new RuntimeException("Not implemented here");
    }

    @Override
    public SoundCloudDTO grabSoundMetaData(String soundCloudLink) {
        throw new RuntimeException("Not implemented here");
    }

    private InstagramMediaDataDTO instagramDtoBuilder(MediaResponse mediaResponse) {
        InstagramMediaDataDTO instagramMediaDataDTO = new InstagramMediaDataDTO();
        instagramMediaDataDTO.setStatus(mediaResponse.getStatus());
        instagramMediaDataDTO.setNextMaxId(mediaResponse.getNextMaxId());
        instagramMediaDataDTO.setStatusCode(mediaResponse.getStatusCode());
        instagramMediaDataDTO.setNumResults(mediaResponse.getMedia().getNumResults());
        instagramMediaDataDTO.setMoreAvailable(mediaResponse.getMedia().getMoreAvailable());
        List<InstagramMediaItem> instagramMediaItemList = new ArrayList<>(itemListBuilder(mediaResponse.getMedia().getRankedItemsList()));
        instagramMediaItemList.addAll(itemListBuilder(mediaResponse.getMedia().getItemsList()));
        instagramMediaDataDTO.setInstagramMediaItemList(instagramMediaItemList);
        return instagramMediaDataDTO;
    }

    private InstagramMediaDataDTO instagramCodeDtoBuilder(MediaResponse mediaResponse) {
        InstagramMediaDataDTO instagramMediaDataDTO = new InstagramMediaDataDTO();
        instagramMediaDataDTO.setStatus(mediaResponse.getStatus());
        instagramMediaDataDTO.setNextMaxId(mediaResponse.getNextMaxId());
        instagramMediaDataDTO.setStatusCode(mediaResponse.getStatusCode());
        instagramMediaDataDTO.setNumResults(mediaResponse.getMedia().getNumResults());
        instagramMediaDataDTO.setMoreAvailable(mediaResponse.getMedia().getMoreAvailable());
        List<InstagramMediaItem> instagramMediaItemList = new ArrayList<>(itemListBuilder(mediaResponse.getMedia().getItemsList()));
        instagramMediaDataDTO.setInstagramMediaItemList(instagramMediaItemList);
        return instagramMediaDataDTO;
    }

    private List<InstagramMediaItem> itemListBuilder(List<Item> itemList) {
        List<InstagramMediaItem> instagramMediaItemList = new ArrayList<>();
        for (Item item : itemList) {
            InstagramMediaItem instagramMediaItem = new InstagramMediaItem();
            instagramMediaItem.setPk(item.getPk());
            instagramMediaItem.setId(item.getId());
            instagramMediaItem.setMediaType(item.getMediaType());
            instagramMediaItem.setVideoDuration(Math.toIntExact(item.getVideoDuration()));
            instagramMediaItem.setCode(item.getCode());
            instagramMediaItem.setCaption(item.getCaption());

            InstagramMediaAuthor instagramMediaAuthor = new InstagramMediaAuthor();
            instagramMediaAuthor.setPk(item.getAuthor().getPk());
            instagramMediaAuthor.setUserName(item.getAuthor().getUsername());
            instagramMediaAuthor.setFullName(item.getAuthor().getFullName());
            instagramMediaAuthor.setProfilePictureUrl(item.getAuthor().getProfilePictureUrl());
            instagramMediaItem.setInstagramMediaAuthor(instagramMediaAuthor);

            InstagramMediaLocation instagramMediaLocation = new InstagramMediaLocation();
            instagramMediaLocation.setPk(item.getLocation().getPk());
            instagramMediaLocation.setName(item.getLocation().getName());
            instagramMediaLocation.setLat(item.getLocation().getLat());
            instagramMediaLocation.setLng(item.getLocation().getLng());
            instagramMediaLocation.setAddress(item.getLocation().getAddress());
            instagramMediaItem.setInstagramMediaLocation(instagramMediaLocation);

            List<InstagramMediaVideo> instagramMediaVideoList = new ArrayList<>();
            List<InstagramMediaImage> instagramMediaImageList = new ArrayList<>();
            for (Image image : item.getImageList()) {
                InstagramMediaImage instagramMediaImage = new InstagramMediaImage();
                instagramMediaImage.setUrl(image.getUrl());
                instagramMediaImage.setWidth(image.getWidth());
                instagramMediaImage.setHeight(image.getHeight());
                instagramMediaImageList.add(instagramMediaImage);
            }
            for (Video video : item.getVideoList()) {
                InstagramMediaVideo instagramMediaVideo = new InstagramMediaVideo();
                instagramMediaVideo.setId(video.getId());
                instagramMediaVideo.setWidth(video.getWidth());
                instagramMediaVideo.setHeight(video.getHeight());
                instagramMediaVideo.setType(video.getType());
                instagramMediaVideo.setUrl(video.getUrl());
                instagramMediaVideoList.add(instagramMediaVideo);
            }
            instagramMediaItem.setInstagramMediaVideoList(instagramMediaVideoList);
            instagramMediaItem.setInstagramMediaImageList(instagramMediaImageList);
            instagramMediaItemList.add(instagramMediaItem);
        }
        return instagramMediaItemList;
    }
}
