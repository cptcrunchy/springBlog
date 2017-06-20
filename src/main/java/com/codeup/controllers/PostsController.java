package com.codeup.controllers;

import com.codeup.svcs.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.codeup.models.Post;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostsController {
    private PostSvc postsDao;

    @Autowired
    public PostsController(PostSvc postsDao) { this.postsDao = postsDao;}


    @GetMapping("/posts")
    @ResponseBody
    public String index(Model model) {
        List<Post> posts = postsDao.findAll();
        model.addAttribute("posts", posts);

        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    @ResponseBody
    public String show(@PathVariable long id) {return "viewing post: " + id;}

}
