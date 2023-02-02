package com.Elysyum.controller;

import com.Elysyum.dto.PostDto;
import com.Elysyum.model.Post;
import com.Elysyum.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> postContent(@Valid @RequestBody PostDto postDto) throws URISyntaxException{

        if (postService.findByTitle(postDto.getTitle())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        postService.save(postDto);
        System.out.println("Post saved Successful" + postDto.getTitle());
        return ResponseEntity.created(new URI("posts/" + postDto.getId())).build();
    }


    @GetMapping
    public List<PostDto> findAll(){

        return postService.findAll().stream().map(post -> modelMapper.map(post,PostDto.class)).toList();
    }

    @GetMapping("/{id}")
    public PostDto findById(@PathVariable("id") long id ){

        Post post = postService.findById(id);
        return new PostDto(id, post.getTitle(), post.getContent(), post.getAuthor(),post.getDate());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable("id") long id ,@RequestBody PostDto post){

        System.out.println("Updated post with id" + id );
        return new ResponseEntity<>(postService.updatePost(id,post),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostDto> deletePost(@Valid @PathVariable("id") long id){

        postService.deleteById(id);
        System.out.println("Deleted post with id " + id);
        return ResponseEntity.ok().build();
    }
}
