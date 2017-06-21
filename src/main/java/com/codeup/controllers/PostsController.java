package com.codeup.controllers;

import com.codeup.svcs.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.codeup.models.Post;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PostsController {
    private final PostSvc postsDao;

    @Autowired
    public PostsController(PostSvc postsDao) { this.postsDao = postsDao;}


    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.findOne(id));
        return "posts/show";
    }

}
