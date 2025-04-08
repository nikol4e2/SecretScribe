package com.example.secretscribe.web.rest;

import com.example.secretscribe.model.Confession;
import com.example.secretscribe.model.dto.ConfessionDto;
import com.example.secretscribe.model.exceptions.ConfessionNotFoundException;
import com.example.secretscribe.service.ConfessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/confession")
@CrossOrigin(origins = "http://localhost:3000")
public class ConfessionRestController {

    private final ConfessionService confessionService;


    public ConfessionRestController(ConfessionService confessionService) {
        this.confessionService = confessionService;
    }


    @GetMapping
    public List<Confession> findAll(){
        return confessionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Confession> findById(@PathVariable Long id){
        Confession confession=this.confessionService.findById(id).orElseThrow(()->new ConfessionNotFoundException(id));
        if(confession!=null)
        {
            return ResponseEntity.ok(confession);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/approved")
    public List<Confession> findAllApproved(){
        return confessionService.findAllApproved();
    }

    @GetMapping("/unapproved")
    public List<Confession> findAllUnapproved(){
        return confessionService.findAllUnapproved();
    }

    @GetMapping("/popular")
    List<Confession> findAllPopular(){
        return confessionService.findAllPopular();
    }

    @PostMapping("/add")
    public ResponseEntity<Confession> addConfession(@RequestBody ConfessionDto confessionDto)
    {
        return this.confessionService.save(confessionDto.getText()).map(confession -> ResponseEntity.ok().body(confession))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Confession> deleteById(@PathVariable Long id)
    {
        this.confessionService.deleteById(id);
        if(this.confessionService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
