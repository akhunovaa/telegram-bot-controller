package com.botmasterzzz.controller.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @Value("${telegram.remote.client.ip}")
    private String host;

    @Value("${telegram.remote.client.port}")
    private int port;

    @Value("${instagram.scrapper.ip}")
    private String instagramScrapperHost;

    @Value("${instagram.scrapper.port}")
    private int instagramScrapperPort;

    @Value("${youtube.scrapper.ip}")
    private String youtubeScrapperHost;

    @Value("${youtube.scrapper.port}")
    private int youtubeScrapperPort;

    @Bean(destroyMethod = "shutdown")
    public ManagedChannel managedChannel() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        return channel;
    }

    @Bean(name = "managedInstagramLoginChannel", destroyMethod = "shutdown")
    public ManagedChannel managedInstagramLoginChannel() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(instagramScrapperHost, instagramScrapperPort)
                .usePlaintext()
                .build();
        return channel;
    }

    @Bean(name = "managedInstagramMediaChannel", destroyMethod = "shutdown")
    public ManagedChannel managedInstagramMediaChannel() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(instagramScrapperHost, instagramScrapperPort)
                .usePlaintext()
                .build();
        return channel;
    }

    @Bean(name = "managedYouTubeMediaChannel", destroyMethod = "shutdown")
    public ManagedChannel managedYouTubeMediaChannel() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(youtubeScrapperHost, youtubeScrapperPort)
                .usePlaintext()
                .build();
        return channel;
    }

}
