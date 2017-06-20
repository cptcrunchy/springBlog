package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

    @GetMapping("/add/{num1}/and/{num2}")
    public String add(@PathVariable int num1, @PathVariable int num2, Model model) {
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        return "arithmetic/addition/result";
    }


}
