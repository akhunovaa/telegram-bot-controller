package com.botmasterzzz.controller.service.impl;

import com.botmasterzzz.controller.service.StorageService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

    @Value("${multipart.file.upload.path}")
    private String path;

    @Override
    @PostConstruct
    public void init() {
        String fullPath = path + "/get_parts";
        try {
            FileUtils.forceMkdir(new File(fullPath));
        } catch (IOException e) {
            logger.error("Can not create dirs for a repository path location {}", fullPath, e);
        }
    }

    @Override
    public void storeMainImage(MultipartFile file, String projectId) {
        String fullPath = path + "/get_parts";
        String usersPathLocation = fullPath + "/project/" + projectId;
        try {
            FileUtils.forceMkdir(new File(usersPathLocation));
        } catch (IOException e) {
            logger.error("Can not create dirs for a user {} path {} location", projectId, usersPathLocation, e);
        }
        String fileExtension = null != file.getContentType() ? file.getContentType().split("/")[1] : "jpg";
        String fileName = usersPathLocation + "/" + "image." + fileExtension;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                logger.error("Cat not upload a {}", fileName, e);
            }
        } else {
            logger.error("Cat not upload a {}" + fileName + " because of empty file");
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(Long projectId) {
        String fullPath = path + "/get_parts";
        String usersPathLocation = fullPath + "/project/" + projectId;
        logger.info("User path location directory {}", usersPathLocation);
        String[] files = FileUtils.getFile(new File(usersPathLocation)).exists() ? FileUtils.getFile(new File(usersPathLocation)).list() : new String[]{"empty"};
        File imageFile = null;
        for (String file : files) {
            imageFile = FileUtils.getFile(new File(usersPathLocation), file);
        }
        logger.debug("User file {} in  directory {}", imageFile.getName(), usersPathLocation);
        return imageFile.toPath();
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
