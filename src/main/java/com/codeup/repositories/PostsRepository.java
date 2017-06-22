package com.codeup.repositories;

import com.codeup.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostsRepository extends CrudRepository<Post, Long> {
    Post findByTitle(String title);
    List<Post> findByTitleIsLike(String title);
    Post findById(long id);
}
