package com.blog.service;


import com.blog.domain.Post;
import com.blog.repository.PostRepository;
import com.blog.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    
    private final PostRepository postRepository;
    
    public Long write(PostCreate postCreate) {
        Post post = Post.createPost(postCreate);
        return postRepository.save(post).getId();
    }
}
