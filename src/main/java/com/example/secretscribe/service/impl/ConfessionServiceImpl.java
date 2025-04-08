package com.example.secretscribe.service.impl;

import com.example.secretscribe.model.Comment;
import com.example.secretscribe.model.Confession;
import com.example.secretscribe.model.exceptions.ConfessionNotFoundException;
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
        Optional<Confession> confession=this.confessionRepository.findById(id);
        if(confession.isPresent()) {
            confession.get().setApproved(true);
            confessionRepository.save(confession.get());

            return confession.get();
        }else throw new ConfessionNotFoundException(id);


    }

    @Override
    public Confession addCommentToConfession(Long id, Comment comment) {
        Optional<Confession> confession=this.confessionRepository.findById(id);
        if(confession.isPresent()) {
            List<Comment> comments=confession.get().getComments();
            comments.add(comment);
            confession.get().setComments(comments);
            confessionRepository.save(confession.get());
            return confession.get();
        }else throw new ConfessionNotFoundException(id);

    }

    @Override
    public void addLikeToConfession(Long id) {
        Optional<Confession> confession=this.confessionRepository.findById(id);

        if(confession.isPresent()) {
            confession.get().setLikes(confession.get().getLikes()+1);
            confessionRepository.save(confession.get());
        }
    }

    @Override
    public void addDislikeToConfession(Long id) {
        Optional<Confession> confession=this.confessionRepository.findById(id);

        if(confession.isPresent()) {
            confession.get().setDislikes(confession.get().getDislikes() + 1);
            confessionRepository.save(confession.get());
        }

    }

    @Override
    public List<Confession> findAllPopular() {
        List<Confession> popular=new ArrayList<>();
        List<Confession> allConfession=this.findAllApproved();
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
