package com.foorun.unieat.controller;

import com.foorun.unieat.constant.SwaggerApiInfo;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.file.dto.FileDetail;
import com.foorun.unieat.service.file.FileUploadService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/upload", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Api(tags = SwaggerApiInfo.POST)
public class UploadController {
    private final FileUploadService fileUploadService;

    @PostMapping
    public ResponseEntity<ApiResponse<FileDetail>> post(
            @RequestPart("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(fileUploadService.save(multipartFile)));
    }
}
