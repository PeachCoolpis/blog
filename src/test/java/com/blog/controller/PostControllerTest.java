package com.blog.controller;

import com.blog.domain.Post;
import com.blog.repository.PostRepository;
import com.blog.request.PostCreate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {
    
    
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PostRepository postRepository;
    
    
    
    @BeforeEach
    void clear() {
        postRepository.deleteAll();
    }
    
    @Test
    @DisplayName("/posts 요청시 Hello World 를 출력한다.")
    void test() throws Exception {
        mockMvc.perform(get("/posts")
                        .contentType(APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"))
                .andDo(print());
    }
    
    
//    @Test
//    @DisplayName("")
//    void test2() throws Exception {
//        PostCreate postCreate = new PostCreate();
//        postCreate.setTitle("");
//        postCreate.setContent("");
//        String json = objectMapper.writeValueAsString(postCreate);
//        mockMvc.perform(post("/posts")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json)
//                )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content").value("내용을 입력해주세요")
//                )
//                .andDo(print());
//    }
    
    @Test
    @DisplayName("/posts 요청시 db에 값이 저장된다")
    void test3() throws Exception {
        PostCreate postCreate = new PostCreate("제목테스트","내용테스트");
        
        String json = objectMapper.writeValueAsString(postCreate);
        mockMvc.perform(post("/posts")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andDo(print());
        
        assertThat(postRepository.count()).isEqualTo(1L);
        
        Post post = postRepository.findAll().get(0);
        assertThat(post.getTitle()).isEqualTo("제목테스트");
        assertThat(post.getContent()).isEqualTo("내용테스트");
    }
    
    @Test
    @DisplayName("글 1개 조회")
    void test4() throws Exception {
        PostCreate postCreate = new PostCreate("123456","내용테스트");
        Post post = Post.createPost(postCreate);
        Post savePost = postRepository.save(post);
       
        
        
        mockMvc.perform(get("/posts/{postId}",savePost.getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savePost.getId()))
                .andExpect(jsonPath("$.title").value(savePost.getTitle().substring(0,Math.min(savePost.getTitle().length(),10))))
                .andExpect(jsonPath("$.content").value(savePost.getContent()))
                .andDo(print());
        
    }
}