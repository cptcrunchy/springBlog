package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.codeup.models.Post;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostsController {

    @GetMapping("/posts")
    public String index(Model model) {
        List<Post> posts = new ArrayList<>();

        posts.add(new Post("Test 1", "Beginning of Post."));
        posts.add(new Post("Test 2","some body"));
        posts.add(new Post("Test 3","some body"));

        model.addAttribute("posts", posts);
        return "posts/index";

    }
    @GetMapping("/posts/{id}")
    public String post(@PathVariable long id, Model model) {

        Post post = new Post("Test", "laskdflasdkflasd");
        model.addAttribute("post", post);
        return "posts/show";
    }

}
