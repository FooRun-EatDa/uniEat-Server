package com.foorun.unieat.controller;

import com.foorun.unieat.constant.SwaggerApiInfo;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.school.dto.School;
import com.foorun.unieat.service.school.SchoolListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/school", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Api(tags = SwaggerApiInfo.SCHOOL)
public class SchoolController {
    private final SchoolListService schoolListService;

    @ApiOperation(value = SwaggerApiInfo.GET_SCHOOL)
    @GetMapping
    public ResponseEntity<ApiResponse<List<School>>> get(
            @RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(schoolListService.fetchList(keyword)));
    }
}
