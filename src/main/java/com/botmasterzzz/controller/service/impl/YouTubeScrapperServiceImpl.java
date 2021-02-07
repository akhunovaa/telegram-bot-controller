package com.botmasterzzz.controller.service.impl;

import com.botmasterzzz.controller.dto.instagram.InstagramMediaDataDTO;
import com.botmasterzzz.controller.dto.soundcloud.SoundCloudDTO;
import com.botmasterzzz.controller.dto.youtube.AdaptiveFormat;
import com.botmasterzzz.controller.dto.youtube.Format;
import com.botmasterzzz.controller.dto.youtube.Thumbnail_;
import com.botmasterzzz.controller.dto.youtube.YouTubeMediaDTO;
import com.botmasterzzz.controller.service.ScrapperService;
import com.proto.instagram.LoginResponse;
import com.proto.youtube.*;
import io.grpc.ManagedChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("youtube")
public class YouTubeScrapperServiceImpl implements ScrapperService {

    private static final Logger LOGGER = LoggerFactory.getLogger(YouTubeScrapperServiceImpl.class);

    private final ManagedChannel managedYouTubeMediaChannel;

    private MediaServiceGrpc.MediaServiceBlockingStub mediaServiceBlockingStub;

    public YouTubeScrapperServiceImpl(ManagedChannel managedYouTubeMediaChannel) {
        this.managedYouTubeMediaChannel = managedYouTubeMediaChannel;
    }

    @PostConstruct
    private void initializeClient() {
        mediaServiceBlockingStub = MediaServiceGrpc.newBlockingStub(managedYouTubeMediaChannel);
    }

    @Override
//    @Cacheable(value = "youtube-media-data", key = "#videoIdentifier")
    public YouTubeMediaDTO grabMediaMetaData(String videoIdentifier) {
        LOGGER.info("Request to YouTube scrapper service => requesting some media from service");

        MediaRequest mediaRequest = MediaRequest
                .newBuilder()
                .setQueryTerm(videoIdentifier)
                .build();

        LOGGER.info("Request to YouTube scrapper service => requesting {}", mediaRequest.toString());
        MediaResponse mediaResponse = mediaServiceBlockingStub.withDeadlineAfter(2, TimeUnit.MINUTES).mediaMetaData(mediaRequest);
        LOGGER.info("Request to YouTube scrapper service => response {}", mediaResponse.toString());

        return youTubeDtoBuilder(mediaResponse);
    }

    @Override
//    @Cacheable(value = "youtube-media-search", key = "#queryTerm")
    public List<YouTubeMediaDTO> searchYouTubeMedia(String queryTerm, long maxResults) {
        LOGGER.info("Request to YouTube scrapper service => requesting some media from service");

        MediaRequest mediaRequest = MediaRequest
                .newBuilder()
                .setQueryTerm(queryTerm)
                .setMaxResults(maxResults)
                .build();

        LOGGER.info("Request to YouTube scrapper service => requesting {}", mediaRequest.toString());
        MediaSearchResponse mediaSearchResponse = mediaServiceBlockingStub.withDeadlineAfter(2, TimeUnit.MINUTES).mediaSearch(mediaRequest);
        LOGGER.info("Request to YouTube scrapper service => response {}", mediaSearchResponse.toString());

        return youTubeSearchDtoListBuilder(mediaSearchResponse);
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
    public SoundCloudDTO grabSoundMetaData(String soundCloudLink) {
        return null;
    }

    private List<YouTubeMediaDTO> youTubeSearchDtoListBuilder(MediaSearchResponse mediaSearchResponse) {
        List<YouTubeMediaDTO> youTubeMediaDTOList = new ArrayList<>();
        for (SearchMedia searchMedia : mediaSearchResponse.getSearchMediasList()) {
            YouTubeMediaDTO youTubeMediaDTO = new YouTubeMediaDTO();
            String videoIdentifier = searchMedia.getId();
            String channelIdentifier = searchMedia.getChannelId();
            String channelTitle = searchMedia.getChannelTitle();
            String description = searchMedia.getDescription();
            long publishedAt = searchMedia.getPublishedAt();
            String title = searchMedia.getTitle();
            List<Thumbnail> thumbnails = searchMedia.getThumbnailsList();
            List<Thumbnail_> thumbnailList = new ArrayList<>();
            for (Thumbnail thumbnail : thumbnails) {
                String url = thumbnail.getUrl();
                int width = thumbnail.getWidth();
                int height = thumbnail.getHeight();
                Thumbnail_ thumbnailI = new Thumbnail_();
                thumbnailI.setUrl(url);
                thumbnailI.setWidth(width);
                thumbnailI.setHeight(height);
                thumbnailList.add(thumbnailI);
            }
            com.botmasterzzz.controller.dto.youtube.Thumbnail thumbnail = new com.botmasterzzz.controller.dto.youtube.Thumbnail();
            thumbnail.setThumbnails(thumbnailList);

            com.botmasterzzz.controller.dto.youtube.VideoDetails videoDetails = new com.botmasterzzz.controller.dto.youtube.VideoDetails();
            videoDetails.setVideoId(videoIdentifier);
            videoDetails.setTitle(title);
            videoDetails.setChannelId(channelIdentifier);
            videoDetails.setThumbnail(thumbnail);
            videoDetails.setShortDescription(description);
            youTubeMediaDTO.setVideoDetails(videoDetails);
            youTubeMediaDTOList.add(youTubeMediaDTO);
        }
        return youTubeMediaDTOList;
    }

    private YouTubeMediaDTO youTubeDtoBuilder(MediaResponse mediaResponse) {
        YouTubeMediaDTO youTubeMediaDTO = new YouTubeMediaDTO();

        String videoIdentifier = mediaResponse.getMedia().getVideoDetails().getVideoId();
        String videoTitle = mediaResponse.getMedia().getVideoDetails().getTitle();
        String videoLength = mediaResponse.getMedia().getVideoDetails().getLengthSeconds();
        List<String> keywords = mediaResponse.getMedia().getVideoDetails().getKeywordsList();
        String shortDescription = mediaResponse.getMedia().getVideoDetails().getShortDescription();
        String author = mediaResponse.getMedia().getVideoDetails().getAuthor();
        String channelIdentifier = mediaResponse.getMedia().getVideoDetails().getChannelId();
        String viewCount = mediaResponse.getMedia().getVideoDetails().getViewCount();
        String expireInSeconds = mediaResponse.getMedia().getStreamingData().getExpireInSeconds();

        List<Thumbnail> thumbnails = mediaResponse.getMedia().getVideoDetails().getThumbnailList();
        List<Thumbnail_> thumbnailList = new ArrayList<>();
        for (Thumbnail thumbnail : thumbnails) {
            String url = thumbnail.getUrl();
            int width = thumbnail.getWidth();
            int height = thumbnail.getHeight();
            Thumbnail_ thumbnailI = new Thumbnail_();
            thumbnailI.setUrl(url);
            thumbnailI.setWidth(width);
            thumbnailI.setHeight(height);
            thumbnailList.add(thumbnailI);
        }
        com.botmasterzzz.controller.dto.youtube.Thumbnail thumbnail = new com.botmasterzzz.controller.dto.youtube.Thumbnail();
        thumbnail.setThumbnails(thumbnailList);

        com.botmasterzzz.controller.dto.youtube.VideoDetails videoDetails = new com.botmasterzzz.controller.dto.youtube.VideoDetails();
        videoDetails.setVideoId(videoIdentifier);
        videoDetails.setTitle(videoTitle);
        videoDetails.setLengthSeconds(videoLength);
        videoDetails.setKeywords(keywords);
        videoDetails.setChannelId(channelIdentifier);
        videoDetails.setShortDescription(shortDescription);
        videoDetails.setThumbnail(thumbnail);
        videoDetails.setViewCount(viewCount);
        videoDetails.setAuthor(author);
        youTubeMediaDTO.setVideoDetails(videoDetails);

        List<Format> formatsList = new ArrayList<>();
        List<AdaptiveFormat> adaptiveFormatsList = new ArrayList<>();
        for (Formats format : mediaResponse.getMedia().getStreamingData().getFormatsList()) {
            int itag = format.getItag();
            String url = format.getUrl();
            String mimeType = format.getMimeType();
            Integer bitRate = format.getBitrate();
            Integer width = format.getWidth();
            Integer height = format.getHeight();
            String lastModified = format.getLastModified();
            String contentLength = format.getContentLength();
            String quality = format.getQuality();
            Integer fps = format.getFps();
            Integer averageBitrate = format.getAverageBitrate();
            Integer audioChannels = format.getAudioChannels();
            String qualityLabel = format.getQualityLabel();
            String projectionType = format.getProjectionType();
            String audioQuality = format.getAudioQuality();
            String approxDurationMs = format.getApproxDurationMs();
            String audioSampleRate = format.getAudioSampleRate();

            Format formats = new Format();
            formats.setItag(itag);
            formats.setUrl(url);
            formats.setMimeType(mimeType);
            formats.setBitrate(bitRate);
            formats.setWidth(width);
            formats.setHeight(height);
            formats.setLastModified(lastModified);
            formats.setContentLength(contentLength);
            formats.setQuality(quality);
            formats.setFps(fps);
            formats.setQualityLabel(qualityLabel);
            formats.setProjectionType(projectionType);
            formats.setAverageBitrate(averageBitrate);
            formats.setAudioQuality(audioQuality);
            formats.setApproxDurationMs(approxDurationMs);
            formats.setAudioSampleRate(audioSampleRate);
            formats.setAudioChannels(audioChannels);
            formatsList.add(formats);
        }

        for (AdaptiveFormats adaptiveFormats : mediaResponse.getMedia().getStreamingData().getAdaptiveFormatsList()) {
            int itag = adaptiveFormats.getItag();
            String url = adaptiveFormats.getUrl();
            String mimeType = adaptiveFormats.getMimeType();
            Integer bitRate = adaptiveFormats.getBitrate();
            Integer width = adaptiveFormats.getWidth();
            Integer height = adaptiveFormats.getHeight();
            String lastModified = adaptiveFormats.getLastModified();
            String contentLength = adaptiveFormats.getContentLength();
            String quality = adaptiveFormats.getQuality();
            Integer fps = adaptiveFormats.getFps();
            Integer averageBitrate = adaptiveFormats.getAverageBitrate();
            Integer audioChannels = adaptiveFormats.getAudioChannels();
            String qualityLabel = adaptiveFormats.getQualityLabel();
            String projectionType = adaptiveFormats.getProjectionType();
            String audioQuality = adaptiveFormats.getAudioQuality();
            String approxDurationMs = adaptiveFormats.getApproxDurationMs();
            String audioSampleRate = adaptiveFormats.getAudioSampleRate();

            AdaptiveFormat adaptiveFormat = new AdaptiveFormat();
            adaptiveFormat.setItag(itag);
            adaptiveFormat.setUrl(url);
            adaptiveFormat.setMimeType(mimeType);
            adaptiveFormat.setBitrate(bitRate);
            adaptiveFormat.setWidth(width);
            adaptiveFormat.setHeight(height);
            adaptiveFormat.setLastModified(lastModified);
            adaptiveFormat.setContentLength(contentLength);
            adaptiveFormat.setQuality(quality);
            adaptiveFormat.setFps(fps);
            adaptiveFormat.setQualityLabel(qualityLabel);
            adaptiveFormat.setProjectionType(projectionType);
            adaptiveFormat.setAverageBitrate(averageBitrate);
            adaptiveFormat.setAudioQuality(audioQuality);
            adaptiveFormat.setApproxDurationMs(approxDurationMs);
            adaptiveFormat.setAudioSampleRate(audioSampleRate);
            adaptiveFormat.setAudioChannels(audioChannels);
            adaptiveFormatsList.add(adaptiveFormat);
        }

        com.botmasterzzz.controller.dto.youtube.StreamingData streamingData = new com.botmasterzzz.controller.dto.youtube.StreamingData();
        streamingData.setExpiresInSeconds(expireInSeconds);
        streamingData.setFormats(formatsList);
        streamingData.setAdaptiveFormats(adaptiveFormatsList);
        youTubeMediaDTO.setStreamingData(streamingData);
        return youTubeMediaDTO;
    }
}
