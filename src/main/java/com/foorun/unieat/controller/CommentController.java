package com.foorun.unieat.controller;

import com.foorun.unieat.constant.SwaggerApiInfo;
import com.foorun.unieat.domain.comment.dto.Comment;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.service.comment.CommentFeelingService;
import com.foorun.unieat.service.comment.CommentListService;
import com.foorun.unieat.service.comment.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Api(tags = SwaggerApiInfo.COMMENT)
public class CommentController {
    private final CommentService commentService;
    private final CommentListService commentListService;
    private final CommentFeelingService commentFeelingService;

    @ApiOperation(value = SwaggerApiInfo.GET_COMMENT_LIST)
    @GetMapping
    public ResponseEntity<ApiResponse<List<Comment>>> get(
            @RequestParam("postId") long postId,
            @ModelAttribute Paging paging) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(commentListService.fetch(postId, paging)));
    }

    @ApiImplicitParam(name = "id", required = true, value = "댓글 고유 ID", example = "1", dataTypeClass = Long.class)
    @ApiOperation(value = SwaggerApiInfo.GET_COMMENT)
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Comment>> get(@PathVariable("id") long commentId) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(commentService.fetch(commentId)));
    }

    @ApiOperation(value = SwaggerApiInfo.POST_COMMENT)
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> post(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody Comment comment) {
        commentService.save(userDetails, comment);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @ApiOperation(value = SwaggerApiInfo.PUT_COMMENT)
    @PutMapping
    public ResponseEntity<ApiResponse<Void>> put(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody Comment comment) {
        commentService.save(userDetails, comment);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @ApiImplicitParam(name = "id", required = true, value = "댓글 고유 ID", example = "1", dataTypeClass = Long.class)
    @ApiOperation(value = SwaggerApiInfo.DELETE_COMMENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") long commentId) {
        commentService.removeSoft(userDetails, commentId);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @ApiImplicitParam(name = "id", required = true, value = "댓글 고유 ID", example = "1", dataTypeClass = Long.class)
    @ApiOperation(value = SwaggerApiInfo.POST_COMMENT_FEELING)
    @PostMapping("/feeling")
    public ResponseEntity<ApiResponse<Void>> postFeeling(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("id") long id) {
        commentFeelingService.feeling(userDetails, id);
        return ResponseEntity
                .ok(ApiResponse.success());
    }

    @ApiImplicitParam(name = "id", required = true, value = "댓글 고유 ID", example = "1", dataTypeClass = Long.class)
    @ApiOperation(value = SwaggerApiInfo.DELETE_COMMENT_FEELING)
    @DeleteMapping("/feeling/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFeeling(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") long id) {
        commentFeelingService.unfeeling(userDetails, id);
        return ResponseEntity
                .ok(ApiResponse.success());
    }

    @ApiImplicitParam(name = "id", required = true, value = "댓글 고유 ID", example = "1", dataTypeClass = Long.class)
    @ApiOperation(value = SwaggerApiInfo.PUT_COMMENT_FEELING)
    @PutMapping("/feeling/toggle/{id}")
    public ResponseEntity<ApiResponse<Void>> putFeeling(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") long id) {
        commentFeelingService.toggleFeeling(userDetails, id);
        return ResponseEntity
                .ok(ApiResponse.success());
    }
}
