package com.Elysyum.service;

import com.Elysyum.dto.PostDto;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasePostTest {

    @Autowired
    private PostService postService;

    private PostDto postDto;

    @BeforeEach
    void setUp(){

        postDto.setId(1L);
        postDto.setTitle("Liberarium Eternium");
        postDto.setContent("Lorem Ipsum has been the industry's standard dummy " +
                "text ever since the 1500s, when an unknown printer took a galley " +
                "of type and scrambled it to make a type specimen book");
        postDto.setAuthor("Jon Doe");
        postDto.setDate(postDto.getDate());

        postService.save(postDto);


    }
}
