package com.apress.quickpoll4.services;

import com.apress.quickpoll4.domain.Poll;
import com.apress.quickpoll4.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PollService {
    @Autowired
    private PollRepository pollRepository;

    //Delete by ID Service
    public void deletePoll(Long id){
        pollRepository.deleteById(id);
    }

    //Find by ID Service
    public Optional<Poll> getById(Long id){
        return pollRepository.findById(id);
    }
}
