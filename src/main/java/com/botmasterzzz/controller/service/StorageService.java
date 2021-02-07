package com.botmasterzzz.controller.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void storeMainImage(MultipartFile file, String projectId);

    Stream<Path> loadAll();

    Path load(Long projectId);

    Resource loadAsResource(String filename);

    void deleteAll();
}
