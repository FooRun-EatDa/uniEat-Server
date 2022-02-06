package com.foorun.unieat.controller;

import com.foorun.unieat.constant.SwaggerApiInfo;
import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.post.dto.Post;
import com.foorun.unieat.domain.post.dto.PostList;
import com.foorun.unieat.service.post.PostListService;
import com.foorun.unieat.service.post.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
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
    public List<PostList> get(@ModelAttribute Paging paging) {
        System.out.println("pageRequest --> " + paging);
        return postListService.fetch(paging);
    }

    @ApiOperation(value = SwaggerApiInfo.GET_POST)
    @GetMapping("/{id}")
    public Post get(@ApiParam(required = true, name = "게시글 고유 ID") @PathVariable("id") long id) {
        return postService.fetch(id);
    }

    @ApiOperation(value = SwaggerApiInfo.POST_POST)
    @PostMapping
    public void post(@RequestBody Post post) {
        postService.save(post);
    }

    @ApiOperation(value = SwaggerApiInfo.DELETE_POST)
    @DeleteMapping("/{id}")
    public void delete(@ApiParam(required = true, name = "게시글 고유 ID") @PathVariable("id") long id) {
        postService.remove(id);
    }
}
