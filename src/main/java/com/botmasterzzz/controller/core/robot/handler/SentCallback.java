package com.botmasterzzz.controller.core.robot.handler;

public interface SentCallback {

    void onResult(String response);

    void onError(RuntimeException apiException);

    void onException(Exception exception);

    String getUrl();
}
