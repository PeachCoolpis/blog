package com.blog.controller;


import com.blog.request.PostCreate;
import com.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {
    
    private final PostService postService;
    
    @PostMapping("/posts")
    public Map<String, Long> post(@Validated @RequestBody PostCreate postCreate) {
        
        return Map.of("postId", postService.write(postCreate));
    }
    
    
}
