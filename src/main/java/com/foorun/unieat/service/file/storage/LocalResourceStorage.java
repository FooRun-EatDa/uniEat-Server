package com.foorun.unieat.service.file.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@ConditionalOnMissingBean(AmazonS3ResourceStorage.class)
@RequiredArgsConstructor
public class LocalResourceStorage implements ResourceStorage {
    @Override
    public void store(String fullPath, MultipartFile multipartFile) {

    }

    @Override
    public void remove(String fullPath) {

    }
}
