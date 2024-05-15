package com.blog.controller;


import com.blog.request.PostCreate;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PostController {
    
    
    @PostMapping("/posts")
    public Map<String, String> post(@Validated @RequestBody PostCreate postCreate) {
        
        
        return Map.of();
    }
    
    
}
