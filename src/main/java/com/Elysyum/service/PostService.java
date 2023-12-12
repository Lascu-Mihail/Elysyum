package com.Elysyum.service;


import com.Elysyum.dto.PostDto;
import com.Elysyum.model.Post;
import com.Elysyum.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class PostService {

    private static final Logger log = getLogger(UserService.class);

    @Autowired
    private PostRepository postRepository;

    public void save(PostDto postDto) {

        Post post = new Post();

        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setAuthor(postDto.getAuthor());
        post.setDate(postDto.getDate());
        log.info("Saving new post");

        postRepository.save(post);

    }


    public List<Post> findAll() {
        log.info("Finding all posts");
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        log.info("Finding post by ID");
        return postRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
    }

    @Transactional
    public void deleteById(Long id) {
        log.info("Deleting post by id");
        postRepository.deleteById(id);

    }

    public Post updatePost(Long postId, PostDto postDetails) {

        Post post = postRepository.findById(postId).get();
        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        post.setAuthor(postDetails.getAuthor());
        post.setDate(postDetails.getDate());

        return postRepository.save(post);
    }

    public boolean findByTitle(String title) {

        return postRepository.findByTitle(title).isPresent();
    }

}
