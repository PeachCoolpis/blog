package com.blog.response;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
public class PostResponse {
    
    private Long id;
    private String title;
    private String content;
    

    
    
}
