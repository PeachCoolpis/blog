package com.blog.controller;


import com.blog.domain.Post;
import com.blog.request.PostCreate;
import com.blog.response.PostResponse;
import com.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {
    
    private final PostService postService;
    
    @PostMapping("/posts")
    public Map<String, Long> post(@Validated @RequestBody PostCreate postCreate) {
        
        return Map.of("postId", postService.write(postCreate));
    }
    
    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        Post post = postService.get(postId);
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle() != null ? post.getTitle().substring(0,Math.min(post.getTitle().length(),10)) : null)
                .content(post.getContent())
                .build();
    }
}
