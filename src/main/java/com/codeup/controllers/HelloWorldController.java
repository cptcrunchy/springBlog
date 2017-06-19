package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloWorldController
{

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello world!";
    }

    @GetMapping("/hello/{name}/{lastName}")
    @ResponseBody
    public String helloFriend(@PathVariable String name, @PathVariable String lastName) {
        return String.format("Hello %s %s!", name, lastName);
    }

    @RequestMapping(path="/increment/{number}", method= RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number) {
        return number + " plus one is " + (number + 1) + "!";
    }
}
