package com.codeup.controllers;

import com.codeup.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.codeup.models.Post;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostsController {
    private PostsRepository postsDao;

    @Autowired
    public PostsController(PostsRepository postsDao) { this.postsDao = postsDao;}

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = postsDao.findAll();

        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model model) {
        Post post = postsDao.findById(id);
        model.addAttribute("post", post);

        return "posts/show";
    }

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

    @GetMapping("/posts/edit/{id}")
    public String editPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.findById(id));
        return "posts/edit";
    }

    @PostMapping("/posts/edit/{id}")
    public String showEditForm(@ModelAttribute Post post, Model model) {
        model.addAttribute("post", postsDao.save(post));
        return "posts/edit";
    }

}
