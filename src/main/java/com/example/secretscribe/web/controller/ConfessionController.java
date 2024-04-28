package com.example.secretscribe.web.controller;

import com.example.secretscribe.service.ConfessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping(value = "/confession")
public class ConfessionController {


    private final ConfessionService confessionService;

    public ConfessionController(ConfessionService confessionService) {
        this.confessionService = confessionService;
    }

    @GetMapping(path = "/add")
    public String getAddConfessionPage()
    {
        return "leaveConfession";
    }

    @PostMapping
    public String addConfession(@RequestParam String text)
    {
        confessionService.save(text);
        return "confessionAddedSuccess";
    }
}
