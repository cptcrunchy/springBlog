package com.codeup.controllers;


import com.codeup.models.Post;
import com.codeup.models.User;
import com.codeup.repositories.UsersRepository;
import com.codeup.svcs.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


@Controller
public class PostsController {

    private final PostSvc postSvc;
    private final UsersRepository usersDao;

    @Value("${file-upload-path}")
    private String uploadPath;

    @Autowired
    public PostsController(PostSvc postSvc, UsersRepository usersDao) {
        this.postSvc = postSvc;
        this.usersDao = usersDao;
    }

    @GetMapping("/posts.json")
    public @ResponseBody
    Iterable<Post> viewAllPosts() {
        return postSvc.findAll();
    }

    @GetMapping("/posts/ajax")
    public String viewAllPostsAjax(){
        return "posts/ajax";
    }

    @GetMapping("/posts")
    public String index(Model model) {

        Iterable<Post> posts = postSvc.findAll();
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
            @RequestParam(name="file") Multipartfile uploadedFile,
            Model model
    ) {
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
        String filename = uploadedFile.getOriginalFilename();
        String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(filepath);

        try {
            uploadedFile.transferTo(destinationFile);
//            model.addAttribute("message", "File successfully uploaded!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong! " + e);
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
