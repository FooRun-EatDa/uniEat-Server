package com.foorun.unieat.controller;

import com.foorun.unieat.constant.SwaggerApiInfo;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.post.dto.Post;
import com.foorun.unieat.domain.post.dto.PostList;
import com.foorun.unieat.service.post.PostListService;
import com.foorun.unieat.service.post.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Api(tags = SwaggerApiInfo.POST)
public class PostController {
    private final PostService postService;
    private final PostListService postListService;

    @ApiOperation(value = SwaggerApiInfo.GET_POST_LIST)
    @GetMapping
    public ResponseEntity<ApiResponse<List<PostList>>> get(@ModelAttribute Paging paging) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(postListService.fetch(paging)));
    }

    @ApiOperation(value = SwaggerApiInfo.GET_POST)
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Post>> get(
            @ApiParam(required = true, name = "게시글 고유 ID") @PathVariable("id") long id) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(postService.fetch(id)));
    }

    @ApiOperation(value = SwaggerApiInfo.POST_POST)
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> post(@RequestBody Post post) {
        return ResponseEntity.ok(
                ApiResponse.execute(() -> postService.save(post)));
    }

    @ApiOperation(value = SwaggerApiInfo.DELETE_POST)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@ApiParam(required = true, name = "게시글 고유 ID") @PathVariable("id") long id) {
        return ResponseEntity.ok(
                ApiResponse.execute(() -> postService.remove(id)));
    }
}
