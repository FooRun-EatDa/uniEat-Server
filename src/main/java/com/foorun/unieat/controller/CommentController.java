package com.foorun.unieat.controller;

import com.foorun.unieat.constant.SwaggerApiInfo;
import com.foorun.unieat.domain.comment.dto.Comment;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.service.comment.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Api(tags = SwaggerApiInfo.COMMENT)
public class CommentController {
    private final CommentService commentService;

    @ApiOperation(value = SwaggerApiInfo.POST_COMMENT)
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> post(@RequestBody Comment comment) {
        commentService.save(comment);
        return ResponseEntity.ok(ApiResponse.success());
    }
}
