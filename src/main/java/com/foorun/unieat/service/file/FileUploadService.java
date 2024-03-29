package com.foorun.unieat.service.file;

import com.foorun.unieat.domain.file.dto.FileDetail;
import com.foorun.unieat.service.file.storage.ResourceStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileUploadService {
    private final ResourceStorage resourceStorage;

    public FileDetail save(MultipartFile multipartFile) {
        FileDetail fileDetail = FileDetail.multipartOf(multipartFile);
        resourceStorage.store(fileDetail.getPath(), multipartFile);
        return fileDetail;
    }
}
