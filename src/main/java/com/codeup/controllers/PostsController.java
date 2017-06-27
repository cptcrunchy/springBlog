package com.codeup.controllers;

import com.codeup.models.Post;
import com.codeup.models.User;
import com.codeup.repositories.UsersRepository;
import com.codeup.svcs.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class PostsController {

    private final PostSvc postSvc;
    private final UsersRepository usersDao;


    @Autowired
    public PostsController(PostSvc postSvc, UsersRepository usersDao) {
        this.postSvc = postSvc;
        this.usersDao = usersDao;
    }

    @GetMapping("/posts")
    public String viewAll(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName(); //get logged in username
        Iterable<Post> posts = postSvc.findAll();

//        model.addAttribute("name", name);
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewIndividualPost(@PathVariable long id, Model model) {
        Post post = postSvc.findOne(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String savePost(
            @Valid Post post,
            Errors validation,
            Model model) {
        if (post.getTitle().endsWith("?")) {
            validation.rejectValue(
            "title",
            "post.title",
            "Special characters not allowed in title"
            );
        }

        if (validation.hasErrors()) {
        model.addAttribute("errors", validation);
        model.addAttribute("post", post);
        return "posts/create";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setOwner(user);
        postSvc.save(post);
        model.addAttribute("post", post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Post post = postSvc.findOne(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/edit/{edit}")
    public String editPost(@ModelAttribute Post post){
        postSvc.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @PostMapping("/post/delete")
    public String deletePost(@ModelAttribute Post post, Model model){
        postSvc.delete(post.getId());
        model.addAttribute("msg", "Your post was deleted correctly");
        return "return the view with a success message";
    }
}
