package com.codeup.svcs;

import com.codeup.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("postSvc")
public class PostSvc {

    private List<Post> posts = new ArrayList<>();

    public PostSvc(){
        createPosts();
    }

    public List<Post> findAll(){
        return posts;
    }

    public Post save(Post post) {
        post.setId(posts.size() + 1);
        posts.add(post);
        return post;
    }

    public Post findOne(long id) {
        return posts.get((int)(id - 1));
    }

    private void createPost(String title, String body) {
        save(new Post(title, body));
    }

    private void createPosts() {

        save(new Post("Help Wanted", "Position details"));
        save(new Post("Help Wanted", "Position details"));
        save(new Post("Help Wanted", "Position details"));
        save(new Post("Help Wanted", "Position details"));
        save(new Post("Help Wanted", "Position details"));

    }

}
