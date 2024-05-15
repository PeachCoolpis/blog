package com.blog.service;

import com.blog.domain.Post;
import com.blog.repository.PostRepository;
import com.blog.request.PostCreate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class PostServiceTest {
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private PostRepository postRepository;
    @Test
    @DisplayName("글작성")
    void test1() {
        PostCreate postCreate = new PostCreate("제목입니다.", "내용입니다.");
        postService.write(postCreate);
        assertThat(1L).isEqualTo(postRepository.count());
    }
    
    @Test
    @DisplayName("글 상세조회")
    void test2() {
        PostCreate postCreate = new PostCreate("제목입니다.", "내용입니다.");
        postService.write(postCreate);
        Long postId = 1L;
        
        Post post = postService.get(postId);
        
        assertThat(post).isNotNull();
    }

    
    
}