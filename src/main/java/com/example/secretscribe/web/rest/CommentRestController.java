package com.example.secretscribe.web.rest;


import com.example.secretscribe.model.Comment;
import com.example.secretscribe.model.Confession;
import com.example.secretscribe.model.dto.CommentDto;
import com.example.secretscribe.model.exceptions.ConfessionNotFoundException;
import com.example.secretscribe.service.CommentService;
import com.example.secretscribe.service.ConfessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentRestController {

    private final CommentService commentService;
    private final ConfessionService confessionService;

    public CommentRestController(CommentService commentService, ConfessionService confessionService) {
        this.commentService = commentService;
        this.confessionService = confessionService;
    }

    @GetMapping("/{id}")
    private List<Comment> showComments(@PathVariable Long id){
        Confession confession=confessionService.findById(id).orElseThrow(()->new ConfessionNotFoundException(id));
        return confession.getComments();

    }

    @PostMapping("/add/{confessionId}")
    public ResponseEntity<Comment> save(@PathVariable Long confessionId,@RequestBody String text)
    {

        Confession confession=confessionService.findById(confessionId).orElseThrow(()->new ConfessionNotFoundException(confessionId));
        Comment comment=commentService.saveComment(text,confession);

        return commentService.findById(comment.getId()).map(comment1 -> ResponseEntity.ok().body(comment1))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<Comment> addLikeToComment(@PathVariable Long id)
    {
        if(commentService.findById(id).isPresent())
        {
            commentService.addLikeToComment(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/dislike/{id}")
    public ResponseEntity<Comment> addDislikeToComment(@PathVariable Long id)
    {
        if(commentService.findById(id).isPresent())
        {
            commentService.addDislikeToComment(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Comment> deleteById(@PathVariable Long id)
    {
        this.commentService.deleteById(id);
        if(this.commentService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}

