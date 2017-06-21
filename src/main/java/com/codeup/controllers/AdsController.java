package com.codeup.controllers;

import com.codeup.models.Ad;
import com.codeup.svcs.AdSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdsController {
    private AdSvc adsDao;

    @GetMapping("ads/create")
    public String showCreateForm(Model model){
        model.addAttribute("ad", new Ad());
        return "ads/create";
    }

    @PostMapping("ads/create")
    public String create(
            @RequestParam(name="title") String title,
            @RequestParam(name="description") String description,
            Model model
    )
    {
        Ad ad = new Ad(title,description);
        ad.setTitle("title");
        ad.setDescription("description");
        return "ads/create";
    }

    @Autowired
    public AdsController(AdSvc adsDao) {
        this.adsDao = adsDao;
    }

    @GetMapping("/ads")
    public String index(Model model) {
        List<Ad> ads = adsDao.findAll();
        model.addAttribute("ads", ads);
        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    @ResponseBody
    public String show(@PathVariable long id) {
        return "viewing ad #" + id;
    }

}
