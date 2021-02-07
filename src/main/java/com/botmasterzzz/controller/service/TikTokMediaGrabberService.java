package com.botmasterzzz.controller.service;

import com.botmasterzzz.controller.dto.ReceivedMediaFile;

import java.io.File;
import java.util.List;

public interface TikTokMediaGrabberService {

    List<ReceivedMediaFile> downloadVideo(String tag, int count, Long chatId);

    File downLoadThumbnail(String url, String fileName);

    File downloadVideo(String url, String fileName);

    List<ReceivedMediaFile> downloadTrends(int count, Long chatId);

    List<ReceivedMediaFile> downloadWatermarkFreeVideo(String tiktokVideoIdentifier, Long chatId);

    List<ReceivedMediaFile> downloadMusicFromVideo(String tiktokMusicIdentifier, Long chatId);

    List<ReceivedMediaFile> downloadVideoFromLink(String tag, Long chatId);

    List<ReceivedMediaFile> getVideoList(String tag, int count, int minCursor, int maxCursor);

}
