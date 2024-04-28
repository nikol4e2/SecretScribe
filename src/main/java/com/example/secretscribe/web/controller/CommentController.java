package com.example.secretscribe.web.controller;

import com.example.secretscribe.model.Comment;
import com.example.secretscribe.model.Confession;
import com.example.secretscribe.service.CommentService;
import com.example.secretscribe.service.ConfessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final ConfessionService confessionService;

    public CommentController(CommentService commentService, ConfessionService confessionService) {
        this.commentService = commentService;
        this.confessionService = confessionService;
    }

    @GetMapping("/{id}")
    public String showComments(@PathVariable Long id, Model model)
    {
        Confession confession=confessionService.findById(id).get();
        model.addAttribute("comments",confession.getComments());
        model.addAttribute("confession",confession);
        return "comments";

    }

    @PostMapping("/add")
    public String addComment(@RequestParam Long confessionId,@RequestParam String text)
    {
        Confession confession=confessionService.findById(confessionId).get();
        Comment comment=this.commentService.saveComment(text);
        confessionService.addCommentToConfession(confessionId,comment);
        return "redirect:/comment/"+confessionId;



    }
}
