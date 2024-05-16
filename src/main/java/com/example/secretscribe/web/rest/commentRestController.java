package com.example.secretscribe.web.rest;


import com.example.secretscribe.model.Comment;
import com.example.secretscribe.model.Confession;
import com.example.secretscribe.model.dto.commentDto;
import com.example.secretscribe.service.CommentService;
import com.example.secretscribe.service.ConfessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class commentRestController {

    private final CommentService commentService;
    private final ConfessionService confessionService;

    public commentRestController(CommentService commentService, ConfessionService confessionService) {
        this.commentService = commentService;
        this.confessionService = confessionService;
    }

    @GetMapping("/{id}")
    private List<Comment> showComments(@PathVariable Long id){
        Confession confession=confessionService.findById(id).get();
        return confession.getComments();
    }

    @PostMapping("/add/{confessionId}")
    public ResponseEntity<Comment> save(@PathVariable Long confessionId,@RequestBody commentDto commentDto)
    {
        Confession confession=confessionService.findById(confessionId).get();
        Comment comment=commentService.saveComment(commentDto);
        confessionService.addCommentToConfession(confessionId,comment);
        return commentService.findById(comment.getId()).map(comment1 -> ResponseEntity.ok().body(comment1))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PostMapping("/like/{id}")
    public ResponseEntity addLikeToComment(@PathVariable Long id)
    {
        if(commentService.findById(id).isPresent())
        {
            commentService.addLikeToComment(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/dislike/{id}")
    public ResponseEntity addDisikeToComment(@PathVariable Long id)
    {
        if(commentService.findById(id).isPresent())
        {
            commentService.addDislikeToComment(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id)
    {
        this.commentService.deleteById(id);
        if(this.commentService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}

