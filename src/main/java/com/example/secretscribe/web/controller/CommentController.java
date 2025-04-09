package com.example.secretscribe.web.controller;

import com.example.secretscribe.model.Comment;
import com.example.secretscribe.model.Confession;
import com.example.secretscribe.model.exceptions.ConfessionNotFoundException;
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

        Confession confession=confessionService.findById(id).orElseThrow(()->new ConfessionNotFoundException(id));
        model.addAttribute("comments",confession.getComments());
        model.addAttribute("confession",confession);
        return "comments";

    }

    @PostMapping("/add")
    public String addComment(@RequestParam Long confessionId,@RequestParam String text)
    {
        Confession confession=confessionService.findById(confessionId).orElseThrow(()->new ConfessionNotFoundException(confessionId));
        Comment comment=this.commentService.saveComment(text,confession);

        confessionService.addCommentToConfession(confessionId,comment);
        return "redirect:/comment/"+confessionId;
    }

    @PostMapping("/like")
    public String addLikeToComment(@RequestParam Long commentId,@RequestParam Long confessionID)
    {
        this.commentService.addLikeToComment(commentId);
        return "redirect:/comment/"+confessionID;
    }


    @PostMapping("/dislike")
    public String addDislikeToComment(@RequestParam Long commentId,@RequestParam Long confessionID)
    {
        this.commentService.addDislikeToComment(commentId);
        return "redirect:/comment/"+confessionID;

    }

    @PostMapping("/delete")
    public String deleteComment(@RequestParam Long commentId,@RequestParam Long confessionId)
    {
        this.commentService.deleteById(commentId);
        return "redirect:/comment/"+confessionId;
    }
}
