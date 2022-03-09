package com.foorun.unieat.controller;

import com.foorun.unieat.constant.ResponseCode;
import com.foorun.unieat.constant.SwaggerApiInfo;
import com.foorun.unieat.domain.common.PostType;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.post.dto.Post;
import com.foorun.unieat.domain.post.dto.PostList;
import com.foorun.unieat.domain.post.dto.PostSavePayload;
import com.foorun.unieat.domain.post.dto.PostSummaryMap;
import com.foorun.unieat.service.post.PostFeelingService;
import com.foorun.unieat.service.post.PostListService;
import com.foorun.unieat.service.post.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/post", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Api(tags = SwaggerApiInfo.POST)
public class PostController {
    private final PostService postService;
    private final PostListService postListService;
    private final PostFeelingService postFeelingService;

    @ApiOperation(value = SwaggerApiInfo.GET_POST_SUMMARY)
    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<PostSummaryMap>> summary() {
        return ResponseEntity.ok(
                ApiResponse.valueOf(postListService.fetchSummary()));
    }

    @ApiImplicitParam(name = "category", required = true, value = "게시글 구분(Best, 맛집, 번개)", example = "LIGHTNING", dataTypeClass = PostType.class)
    @ApiOperation(value = SwaggerApiInfo.GET_POST_LIST)
    @GetMapping
    public ResponseEntity<ApiResponse<List<PostList>>> get(
            @RequestParam("category") PostType postType,
            @ModelAttribute Paging paging) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(postListService.fetch(postType, paging)));
    }

    @ApiImplicitParam(name = "id", required = true, value = "게시글 고유 ID", example = "1", dataTypeClass = Long.class)
    @ApiOperation(value = SwaggerApiInfo.GET_POST)
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Post>> get(@PathVariable("id") long id) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(postService.fetch(id)));
    }

    @ApiOperation(value = SwaggerApiInfo.POST_POST)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Long>> post(
            @AuthenticationPrincipal MemberUserDetails memberUserDetails,
            @RequestBody PostSavePayload postSavePayload) {
        Long postId = postService.save(memberUserDetails, postSavePayload);
        return ResponseEntity
                .created(URI.create(String.format("/post/%d", postId)))
                .body(ApiResponse.of(ResponseCode.CODE_201, postId));
    }

    @ApiOperation(value = SwaggerApiInfo.PUT_POST)
    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Long>> put(
            @AuthenticationPrincipal MemberUserDetails memberUserDetails,
            @RequestBody PostSavePayload postSavePayload) {
        Long postId = postService.modify(memberUserDetails, postSavePayload);
        return ResponseEntity
                .created(URI.create(String.format("/post/%d", postId)))
                .body(ApiResponse.of(ResponseCode.CODE_201, postId));
    }

    @ApiImplicitParam(name = "id", required = true, value = "게시글 고유 ID", example = "1", dataTypeClass = Long.class)
    @ApiOperation(value = SwaggerApiInfo.DELETE_POST)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @AuthenticationPrincipal MemberUserDetails memberUserDetails,
            @PathVariable("id") long id) {
        postService.removeSoft(memberUserDetails, id);
        return ResponseEntity
                .ok(ApiResponse.success());
    }

    @ApiImplicitParam(name = "id", required = true, value = "게시글 고유 ID", example = "1", dataTypeClass = Long.class)
    @ApiOperation(value = SwaggerApiInfo.POST_POST_FEELING)
    @PostMapping("/feeling")
    public ResponseEntity<ApiResponse<Void>> postFeeling(
            @AuthenticationPrincipal MemberUserDetails memberUserDetails,
            @RequestParam("id") long id) {
        postFeelingService.feeling(memberUserDetails, id);
        return ResponseEntity
                .ok(ApiResponse.success());
    }

    @ApiImplicitParam(name = "id", required = true, value = "게시글 고유 ID", example = "1", dataTypeClass = Long.class)
    @ApiOperation(value = SwaggerApiInfo.DELETE_POST_FEELING)
    @DeleteMapping("/feeling/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFeeling(
            @AuthenticationPrincipal MemberUserDetails memberUserDetails,
            @PathVariable("id") long id) {
        postFeelingService.unfeeling(memberUserDetails, id);
        return ResponseEntity
                .ok(ApiResponse.success());
    }

    @ApiImplicitParam(name = "id", required = true, value = "게시글 고유 ID", example = "1", dataTypeClass = Long.class)
    @ApiOperation(value = SwaggerApiInfo.PUT_POST_FEELING)
    @PutMapping("/feeling/toggle/{id}")
    public ResponseEntity<ApiResponse<Void>> putFeeling(
            @AuthenticationPrincipal MemberUserDetails memberUserDetails,
            @PathVariable("id") long id) {
        postFeelingService.toggleFeeling(memberUserDetails, id);
        return ResponseEntity
                .ok(ApiResponse.success());
    }
}
