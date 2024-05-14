package com.example.secretscribe.service.impl;

import com.example.secretscribe.model.Comment;
import com.example.secretscribe.model.Confession;
import com.example.secretscribe.repository.ConfessionRepository;
import com.example.secretscribe.service.ConfessionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ConfessionServiceImpl implements ConfessionService {

    private final ConfessionRepository confessionRepository;
    private final int popularityNumber=100;


    public ConfessionServiceImpl(ConfessionRepository confessionRepository) {
        this.confessionRepository = confessionRepository;
    }

    @Override
    public Optional<Confession> save(String text) {
        Confession confession=new Confession(text, LocalDate.now());
        this.confessionRepository.save(confession);
        return Optional.of(confession);

    }

    @Override
    public List<Confession> findAll() {
        return this.confessionRepository.findAll();
    }

    @Override
    public List<Confession> findByText(String text) {
        return this.confessionRepository.findAllByTextContaining(text);
    }

    @Override
    public Optional<Confession> findById(Long id) {
        return this.confessionRepository.findById(id);
    }

    @Override
    public Confession approveConfession(Long id) {
        Confession confession=this.confessionRepository.findById(id).get();
        if(confession!=null) {
            confession.setApproved(true);
            confessionRepository.save(confession);
        }
        return confession;
    }

    @Override
    public Confession addCommentToConfession(Long id, Comment comment) {
        Confession confession=this.confessionRepository.findById(id).get();
        if(confession!=null) {
            List<Comment> comments=confession.getComments();
            comments.add(comment);
            confession.setComments(comments);
            confessionRepository.save(confession);
        }
        return confession;
    }

    @Override
    public void addLikeToConfession(Long id) {
        Confession confession=this.confessionRepository.findById(id).get();
        if(confession!=null) {
            confession.setLikes(confession.getLikes()+1);
            confessionRepository.save(confession);
        }
    }

    @Override
    public void addDislikeToConfession(Long id) {
        Confession confession=this.confessionRepository.findById(id).get();
        if(confession!=null) {
            confession.setDislikes(confession.getDislikes()+1);
            confessionRepository.save(confession);
        }
    }

    @Override
    public List<Confession> findAllPopular() {
        List<Confession> popular=new ArrayList<>();
        List<Confession> allConfession=findAllApproved();
        for(int i=0;i<allConfession.size();i++)
        {
            Confession confession=allConfession.get(i);
            if(confession.getLikes()+confession.getDislikes()>=popularityNumber)
            {
                popular.add(confession);
            }
        }
        return popular;
    }

    @Override
    public List<Confession> findAllApproved() {
       List<Confession> confessions=confessionRepository.findAll();
       confessions.removeIf(i->!i.isApproved());
       return confessions;
    }

    @Override
    public List<Confession> findAllUnapproved() {
        List<Confession> confessions=confessionRepository.findAll();
        confessions.removeIf(i->i.isApproved());
        return confessions;
    }

    @Override
    public void deleteById(Long id) {
        this.confessionRepository.deleteById(id);
    }
}
