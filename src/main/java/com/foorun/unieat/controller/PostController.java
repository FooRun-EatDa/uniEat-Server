package com.foorun.unieat.controller;

import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.post.dto.Post;
import com.foorun.unieat.domain.post.dto.PostList;
import com.foorun.unieat.service.post.PostListService;
import com.foorun.unieat.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostListService postListService;

    @GetMapping
    public List<PostList> get(@ModelAttribute Paging paging) {
        System.out.println("pageRequest --> " + paging);
        return postListService.fetch(paging);
    }

    @GetMapping("/{id}")
    public Post get(@PathVariable("id") long id) {
        return postService.fetch(id);
    }

    @PostMapping
    public void post(@RequestBody Post post) {
        postService.save(post);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        postService.remove(id);
    }
}
