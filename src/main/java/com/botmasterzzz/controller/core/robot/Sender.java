package com.botmasterzzz.controller.core.robot;


import com.botmasterzzz.controller.core.robot.handler.SentCallback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.botmasterzzz.controller.core.Constants.SOCKET_TIMEOUT;


public abstract class Sender extends HttpSender {

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    private final ExecutorService exe;
    private final CloseableHttpClient httpclient;
    private volatile RequestConfig requestConfig;

    protected Sender() {
        super();
        this.exe = Executors.newFixedThreadPool(100);
        httpclient = HttpClientBuilder.create()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .setConnectionTimeToLive(100, TimeUnit.SECONDS)
                .setMaxConnTotal(100)
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();

        if (requestConfig == null) {
            requestConfig = RequestConfig.copy(RequestConfig.custom().build())
                    .setSocketTimeout(SOCKET_TIMEOUT)
                    .setConnectTimeout(SOCKET_TIMEOUT)
                    .setConnectionRequestTimeout(SOCKET_TIMEOUT)
//                    .setProxy(new HttpHost(options.getProxyHost(), options.getProxyPort(), options.getProxyType()))
                    .build();
        }
    }

    @Override
    protected final <Callback extends SentCallback> void sendApiMethodAsync(Callback callback) {
        //noinspection Convert2Lambda
        exe.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    String responseContent = sendGetMethodRequest(callback.getUrl());
                    try {
                        callback.onResult(responseContent);
                    } catch (RuntimeException e) {
                        callback.onError(e);
                    }
                } catch (IOException e) {
                    callback.onException(e);
                }

            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    protected final String sendApiMethod(String url) {
        String responseContent = null;
        try {
            responseContent = sendGetMethodRequest(url);

        } catch (IOException e) {
            logger.error("Unable to execute ", e);
        }
        return responseContent;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected final String sendApiMethod(String url, String videoUrl) {
        String responseContent = null;
        try {
            responseContent = sendPostMethodRequest(url, videoUrl);

        } catch (IOException e) {
            logger.error("Unable to execute ", e);
        }
        return responseContent;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected final String sendKinoSearchMethod(String url, String referer) {
        String responseContent = null;
        try {
            responseContent = sendKinoGetMethodRequest(url, referer);

        } catch (IOException e) {
            logger.error("Unable to execute ", e);
        }
        return responseContent;
    }


    @Override
    File getFile(String url, File file, String userAgent, String refererHeader, String cookie) {
        File responseFile = file;
        try {
            responseFile = getFileRequest(url, file, userAgent, refererHeader, cookie);
        } catch (IOException e) {
            logger.error("Unable to execute ", e);
        }
        return responseFile;
    }

    @Override
    File getFile(String url, File file) {
        File responseFile = file;
        try {
            responseFile = getFileRequest(url, file);
        } catch (IOException e) {
            logger.error("Unable to execute ", e);
        }
        return responseFile;
    }

    @Override
    File getFileWatermark(String url, File file, String userAgent, String cookie) {
        File responseFile = file;
        try {
            responseFile = getFileRequestWater(url, file, userAgent, cookie);
        } catch (IOException e) {
            logger.error("Unable to execute ", e);
        }
        return responseFile;
    }

    @Override
    String invokeClearFileId(String url, String userAgent, String refererHeader, String cookie) {
        String response = null;
        try {
            response = getClearRequest(url, userAgent, refererHeader, cookie);
        } catch (IOException e) {
            logger.error("Unable to execute ", e);
        }
        return response;
    }

    private String sendGetMethodRequest(String url) throws IOException {
        HttpGet httpGet = configuredHttpGet(url);
        return sendHttpGetRequest(httpGet);
    }

    private String sendKinoGetMethodRequest(String url, String referer) throws IOException {
        HttpGet httpGet = configuredKinoHttpGet(url, referer);
        return sendHttpGetRequest(httpGet);
    }

    private String sendPostMethodRequest(String url, String videoUrl) throws IOException {
        HttpPost httpPost = configuredHttpPost(url, videoUrl);
        return sendHttpPostRequest(httpPost);
    }

    private File getFileRequest(String url, File file, String userAgent, String refererHeader, String cookie) throws IOException {
        HttpGet httpGet = configuredHttpGet(url, userAgent, refererHeader, cookie);
        return sendHttpGetFileRequest(httpGet, file);
    }

    private File getFileRequest(String url, File file) throws IOException {
        HttpGet httpGet = configuredHttpGet(url);
        return sendHttpGetFileRequest(httpGet, file);
    }

    private File getFileRequestWater(String url, File file, String userAgent, String cookie) throws IOException {
        return sendHttpGetFileRequest2(url, file, userAgent, cookie);
    }

    private String getClearRequest(String url, String userAgent, String refererHeader, String cookie) throws IOException {
        HttpGet httpGet = configuredHttpGet(url, userAgent, refererHeader, cookie);
        return sendHttpGetClearRequest(httpGet);
    }

    private String sendHttpPostRequest(HttpPost httppost) throws IOException {
        try (CloseableHttpResponse response = httpclient.execute(httppost)) {
            HttpEntity ht = response.getEntity();
            BufferedHttpEntity buf = new BufferedHttpEntity(ht);
            return EntityUtils.toString(buf, StandardCharsets.UTF_8);
        }
    }

    private String sendHttpGetRequest(HttpGet httpGet) throws IOException {
        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            HttpEntity ht = response.getEntity();
            BufferedHttpEntity buf = new BufferedHttpEntity(ht);
            return EntityUtils.toString(buf, StandardCharsets.UTF_8);
        }
    }

    private String sendHttpGetClearRequest(HttpGet httpGet) throws IOException {
        String fullUrl = null;
        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            HttpEntity ht = response.getEntity();
            if (ht != null) {
                try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ht.getContent()))) {
                    final StringBuilder stringBuffer = new StringBuilder();

                    String readLine;
                    while ((readLine = bufferedReader.readLine()) != null) {
                        stringBuffer.append(readLine);
                        if (stringBuffer.toString().contains("vid:")) {
                            if (stringBuffer.substring(stringBuffer.indexOf("vid:")).startsWith("vid:")) {
                                final String substring = stringBuffer.substring(stringBuffer.indexOf("vid:"));
                                final String trim = substring.substring(4, substring.indexOf("%")).replaceAll("[^A-Za-z0-9]", "").trim();
                                //return "https://api2-16-h2.musical.ly/aweme/v1/play/?video_id=" + trim + "&ratio=default&improve_bitrate=1";
                                return "https://api2-16-h2.musical.ly/aweme/v1/play/?video_id=v09044e60000blglnguer61atuoq2kl0";
                            }
                        }
                    }
                }
            }
            return null;
        }
    }

    private File sendHttpGetFileRequest(HttpGet httpGet, File file) throws IOException {
        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            HttpEntity ht = response.getEntity();
            if (ht != null) {
                try (BufferedInputStream bis = new BufferedInputStream(ht.getContent())) {
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                    int inByte;
                    while ((inByte = bis.read()) != -1) bos.write(inByte);
                    bis.close();
                    bos.close();
                }
            }
            return file;
        }
    }

    private File sendHttpGetFileRequest2(String url, File file, String userAgent, String cookie) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("User-Agent", userAgent)
                .addHeader("Referer", "https://www.tiktok.com/")
                .addHeader("Cookie", cookie)
                .build();
        Response response = client.newCall(request).execute();

        ResponseBody body = response.body();
        BufferedSource source = body.source();

        BufferedSink sink = Okio.buffer(Okio.sink(file));
        Buffer sinkBuffer = sink.buffer();

        long totalBytesRead = 0;
        int bufferSize = 8 * 1024;
        for (long bytesRead; (bytesRead = source.read(sinkBuffer, bufferSize)) != -1; ) {
            sink.emit();
            totalBytesRead += bytesRead;
        }
        sink.flush();
        sink.close();
        source.close();
        return file;
    }

    private HttpPost configuredHttpPost(String url, String videoUrl) {
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36");
        httppost.addHeader("Content-Type", "application/json");
        try {
            StringEntity stringEntity = new StringEntity(videoUrl);
            httppost.setEntity(stringEntity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httppost.setConfig(requestConfig);
        return httppost;
    }

    private HttpGet configuredHttpGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36");
        httpGet.setConfig(requestConfig);
        return httpGet;
    }

    private HttpGet configuredKinoHttpGet(String url, String referer) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader(":authority", "kino.pub");
        httpGet.addHeader(":method", "GET");
        httpGet.addHeader(":path", "/libs/jwplayer-8.11.8/translations/ru.json");
        httpGet.addHeader("accept-encoding", "gzip, deflate, br");
        httpGet.addHeader("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        httpGet.addHeader("cache-control", "no-cache");
        httpGet.addHeader("cookie", "_ga=GA1.2.263996964.1572981224; _gid=GA1.2.4627896.1605179558; PHPSESSID=dttes5cujv57oebo5hoc6fh4t2; _identity=dfc94de2bc9284e3ac7d7785d845c4a1171ca7d4206035af0405bef5c36db787a%3A2%3A%7Bi%3A0%3Bs%3A9%3A%22_identity%22%3Bi%3A1%3Bs%3A51%3A%22%5B557380%2C%229G2dkO68tTgpiwkRI96Kde2MJ8bVmOlc%22%2C7776000%5D%22%3B%7D; _csrf=12a504ed25b682f6e541b5f16b973d2e5951e59ba01b00aa781713b9d1499e49a%3A2%3A%7Bi%3A0%3Bs%3A5%3A%22_csrf%22%3Bi%3A1%3Bs%3A32%3A%22NupTc_STFmpVlaIUO2zrgFR6q_U3N4tf%22%3B%7D; token=b028fb55f9b9493fac14333ee86dda63ed3dc77f3efc5d1f263f36ee9c28388ba%3A2%3A%7Bi%3A0%3Bs%3A5%3A%22token%22%3Bi%3A1%3Bs%3A64%3A%22reW0waz9-SXPY4EwpLFWJ1w0VDmsm3_ssBgchLreYRtnACM1CCLUxPYdwMN1LVZ3%22%3B%7D");
        httpGet.addHeader("pragma", "no-cache");
        httpGet.addHeader("referer", referer);
        httpGet.addHeader("sec-fetch-dest", "empty");
        httpGet.addHeader("sec-fetch-mode", "cors");
        httpGet.addHeader("sec-fetch-site", "same-origin");
        httpGet.addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36");
        httpGet.setConfig(requestConfig);
        return httpGet;
    }

    private HttpGet configuredHttpGet(String url, String userAgent, String refererHeader, String cookie) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("User-Agent", userAgent);
        httpGet.addHeader("Host", httpGet.getURI().getHost());
        httpGet.addHeader("Referer", refererHeader);
        httpGet.addHeader("Cookie", cookie);
        httpGet.setConfig(requestConfig);
        return httpGet;
    }
}
