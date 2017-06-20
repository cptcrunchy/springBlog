package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class PagesController {

    @GetMapping("/resume")
    public String resume() {
        return "resume";
    }

    @GetMapping("/portfolio")
    public String portfolio() {
        return "portfolio";
    }

    @GetMapping("/roll-dice")
    public String roll(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/result/{guess}")
    public String guess(@PathVariable int guess, Model model) {
    int answer = new Random().nextInt((10)+1);
    model.addAttribute("guess", guess);
    model.addAttribute("answer", answer);
    model.addAttribute("win", answer == guess);
      return "roll-dice";
    }
}
