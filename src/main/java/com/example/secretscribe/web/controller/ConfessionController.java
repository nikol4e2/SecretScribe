package com.example.secretscribe.web.controller;

import com.example.secretscribe.model.Confession;
import com.example.secretscribe.model.exceptions.ConfessionIsEmptyException;
import com.example.secretscribe.service.ConfessionService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getAddConfessionPage(@RequestParam(required = false) String error, Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        return "leaveConfession";
    }

    @PostMapping(path = "/add")
    public String addConfession(@RequestParam String text)
    {
        if(text.isEmpty())
        {
            //throw new ConfessionIsEmptyException();
            return "redirect:/confession/add?error=ConfessionTextIsEmpty";
        }
        confessionService.save(text);
        return "redirect:/confession/add/success";
    }

    @GetMapping("/add/success")
    public String getAddSuccessPage()
    {
        return "confessionAddedSuccess";
    }

    @PostMapping(path = "/like")
    public String addLike(@RequestParam Long id)
    {
        if(confessionService.findById(id).isPresent()) {
            confessionService.addLikeToConfession(id);
        }
        return "redirect:/";
    }

    @PostMapping(path = "/dislike")
    public String addDislike(@RequestParam Long id)
    {
        if(confessionService.findById(id).isPresent())
        {
            confessionService.addDislikeToConfession(id);
        }
        return "redirect:/";
    }
}
