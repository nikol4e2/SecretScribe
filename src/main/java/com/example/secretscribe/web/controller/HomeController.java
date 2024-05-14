package com.example.secretscribe.web.controller;

import com.example.secretscribe.service.ConfessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    private final ConfessionService confessionService;


    public HomeController(ConfessionService confessionService) {
        this.confessionService = confessionService;
    }

    @GetMapping
    private String getHomePage(Model model)
    {
        //model.addAttribute("confessions",confessionService.findAllApproved());
        model.addAttribute("confessions",confessionService.findAll());
        return "home";
    }

    @GetMapping(path = "popular")
    private String getPopularPage(Model model)
    {
        model.addAttribute("confessions",confessionService.findAllPopular());
        return "home";
    }
}
