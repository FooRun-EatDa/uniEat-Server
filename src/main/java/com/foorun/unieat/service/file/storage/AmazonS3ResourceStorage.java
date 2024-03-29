package com.foorun.unieat.service.file.storage;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.foorun.unieat.exception.UniEatLogicalException;
import com.foorun.unieat.util.MultipartUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
@ConditionalOnProperty(prefix = "cloud.aws.s3", name = "bucket")
@RequiredArgsConstructor
public class AmazonS3ResourceStorage implements ResourceStorage {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3Client amazonS3Client;

    @Override
    public void store(String fullPath, MultipartFile multipartFile) {
        File file = new File(MultipartUtil.getLocalHomeDirectory(), fullPath);
        try {
            multipartFile.transferTo(file);
            amazonS3Client.putObject(new PutObjectRequest(bucket, fullPath, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            e.printStackTrace();
            throw new UniEatLogicalException();
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @Override
    public void remove(String fullPath) {
        if (amazonS3Client.doesObjectExist(bucket, fullPath)) {
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fullPath));
        }
    }
}
