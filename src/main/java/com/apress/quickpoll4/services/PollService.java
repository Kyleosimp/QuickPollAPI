package com.apress.quickpoll4.services;

import com.apress.quickpoll4.domain.Poll;
import com.apress.quickpoll4.exception.ResourceNotFoundException;
import com.apress.quickpoll4.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
@Service
public class PollService {
    @Autowired
    private PollRepository pollRepository;

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Poll poll = pollRepository.findById(pollId).get();
        if (poll == null){
            throw new ResourceNotFoundException("Poll with id of" + pollId + "not found.");
        }
    }

    //Delete by ID Service
    public void deletePoll(Long id){
        verifyPoll(id);
        pollRepository.deleteById(id);
    }

    //Find by ID Service
    public Optional<Poll> getById(Long id){
        return pollRepository.findById(id);
    }

//    public ResponseEntity<?> getPoll(Long pollId){
//
//        verifyPoll(pollId);
//
//        Poll p = pollRepository.findById(pollId).orElse(null);
//
//        if (p == null){
//            throw new ResourceNotFoundException("Throw works");
//        }
//        return new ResponseEntity<>(p, HttpStatus.OK);
//    }

    //Create Poll Service
    public ResponseEntity<?> createPoll(Poll poll){
        poll = pollRepository.save(poll);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //Update Poll Service
    public ResponseEntity<?> updatePoll (Poll poll,Long pollId){
        verifyPoll(pollId);

        Poll p = pollRepository.save(poll);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Iterable<Poll>> getAllPolls(){
        Iterable<Poll> allPolls = pollRepository.findAll();

        return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
    }

}
