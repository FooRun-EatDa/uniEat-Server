package com.foorun.unieat.admin.controller;

import com.foorun.unieat.admin.domain.code.dto.ACategory;
import com.foorun.unieat.admin.service.ACodeListService;
import com.foorun.unieat.constant.JwtConstant;
import com.foorun.unieat.domain.common.api.ApiResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.foorun.unieat.constant.JwtConstant.HEADER_NAME_REFRESH_TOKEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = ACodeController.MAPPING_URI, produces = APPLICATION_JSON_VALUE)
@CrossOrigin(allowCredentials = "true", originPatterns = "*", exposedHeaders = {JwtConstant.HEADER_NAME, HEADER_NAME_REFRESH_TOKEN, HttpHeaders.LOCATION})
@RequiredArgsConstructor
@Api(hidden = true)
public class ACodeController {
    public final static String MAPPING_URI = "/admin/code";
    private final ACodeListService codeListService;

    @GetMapping(path = "/category")
    public ResponseEntity<ApiResponse<List<ACategory>>> getCategory() {
        return ResponseEntity.ok(
                ApiResponse.valueOf(codeListService.fetch()));
    }
}
