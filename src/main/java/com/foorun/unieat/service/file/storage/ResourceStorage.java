package com.foorun.unieat.service.file.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

public interface ResourceStorage {
    Logger log = LoggerFactory.getLogger(ResourceStorage.class);

    void store(String directoryPath, MultipartFile multipartFile);
    void remove(String directoryPath);

    @PostConstruct
    default void postConstruct() {
        log.info("Activate ResourceStorage type  ==> {}", this.getClass().getName());
    }
}
