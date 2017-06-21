package com.codeup.controllers;

import com.codeup.svcs.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.codeup.models.Post;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostsController {
    private final PostSvc postsDao;

    @Autowired
    public PostsController(PostSvc postsDao) { this.postsDao = postsDao;}


    @GetMapping("/posts/create")
    public String showPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String savePost(@ModelAttribute Post post, Model model) {
        postsDao.save(post);
        model.addAttribute("post", post);
        return "redirect:/posts";
    }



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

    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.findOne(id));
        return "posts/edit";
    }


}
