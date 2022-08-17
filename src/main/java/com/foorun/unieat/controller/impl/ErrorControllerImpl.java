package com.foorun.unieat.controller.impl;

import com.foorun.unieat.constant.ResponseCode;
import com.foorun.unieat.domain.common.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/error")
@RequiredArgsConstructor
@ApiIgnore
public class ErrorControllerImpl implements ErrorController {
    private final HttpServletResponse response;

    @RequestMapping
    public ResponseEntity<ApiResponse<Void>> handle(HttpServletResponse response) {
        HttpStatus httpStatus = HttpStatus.valueOf(response.getStatus());
        return ResponseEntity
                .status(httpStatus)
                .body(ApiResponse.error(ResponseCode.findByHttpStatus(httpStatus)));
    }
}
