package com.Elysyum.repository;

import com.Elysyum.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{


    Optional<Post> findByTitle(String title);
}
