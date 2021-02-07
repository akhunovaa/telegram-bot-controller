package com.botmasterzzz.controller.core.robot;


import com.botmasterzzz.controller.core.robot.handler.SentCallback;

import java.io.File;

public abstract class HttpSender {

    public <Callback extends SentCallback> void executeAsync(Callback callback) throws RuntimeException {
        if (callback == null) {
            throw new RuntimeException("Parameter callback can not be null");
        }
        sendApiMethodAsync(callback);
    }

    public <T> T execute(String url) throws RuntimeException {
        return sendApiMethod(url);
    }

    public <T> T executeKino(String url, String referer) throws RuntimeException {
        return sendKinoSearchMethod(url, referer);
    }

    public <T> T execute(String url, String videoUrl) throws RuntimeException {
        return sendApiMethod(url, videoUrl);
    }

    public File executeMedia(String url, File file, String userAgent, String refererHeader, String cookie) throws RuntimeException {
        return getFile(url, file, userAgent, refererHeader, cookie);
    }

    public File executeMedia(String url, File file) throws RuntimeException {
        return getFile(url, file);
    }

    public File executeWatermarkMedia(String url, File file, String userAgent, String cookie) throws RuntimeException {
        return getFileWatermark(url, file, userAgent, cookie);
    }

    public String requestClearFileId(String url, String userAgent, String refererHeader, String cookie) throws RuntimeException {
        return invokeClearFileId(url, userAgent, refererHeader, cookie);
    }

    abstract <Callback extends SentCallback> void sendApiMethodAsync(Callback callback);

    abstract <T> T sendApiMethod(String url);

    abstract <T> T sendKinoSearchMethod(String url, String referer);

    abstract <T> T sendApiMethod(String url, String videoUrl);

    abstract File getFile(String url, File file, String userAgent, String refererHeader, String cookie);

    abstract File getFile(String url, File file);

    abstract File getFileWatermark(String url, File file, String userAgent, String cookie);

    abstract String invokeClearFileId(String url, String userAgent, String refererHeader, String cookie);

}
