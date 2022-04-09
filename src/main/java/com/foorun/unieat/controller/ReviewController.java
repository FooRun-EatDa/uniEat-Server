package com.foorun.unieat.controller;


import com.foorun.unieat.constant.SwaggerApiInfo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/review", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Api(tags= SwaggerApiInfo.Review)
public class ReviewController {


}
