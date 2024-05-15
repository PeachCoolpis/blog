package com.blog.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostCreate {
    
    @NotBlank(message = "제목을 입력해주세요")
    public String title;
    @NotBlank(message = "내용을 입력해주세요")
    public String content;
    
    public PostCreate() {
    }
    
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
