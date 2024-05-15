package com.blog.domain;


import com.blog.request.PostCreate;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    @Lob
    private String content;
    
    public static Post createPost(PostCreate postCreate) {
        Post post = new Post();
        post.content = postCreate.getContent();
        post.title = postCreate.getTitle();
        return post;
    }
    
}
