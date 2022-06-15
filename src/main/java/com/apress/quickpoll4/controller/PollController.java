package com.apress.quickpoll4.controller;

import com.apress.quickpoll4.domain.Poll;
import com.apress.quickpoll4.exception.ResourceNotFoundException;
import com.apress.quickpoll4.repository.PollRepository;
import com.apress.quickpoll4.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class PollController {

    @Autowired
    private PollService pollService;

    @Autowired
    private PollRepository pollRepository;

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException{
        Poll poll = pollRepository.findById(pollId).get();
        if (poll == null){
            throw new ResourceNotFoundException("Poll with id of" + pollId + "not found.");
        }
    }

    //returns ALL polls contained within table
    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls(){
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
    }
//    @GetMapping("/polls")
//    public Iterable<Poll> getAllPolls(){
//        return demo;
//    }


    //post/create request
//    @RequestMapping(value = "/polls", method = RequestMethod.POST)
//    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll){
//
//        poll = pollRepository.save(poll);
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newPollUri = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("{pollId}")
//                .buildAndExpand(poll.getId())
//                .toUri();
//        responseHeaders.setLocation(newPollUri);
//        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
//
//    }

    //new poll creation
    @PostMapping("/polls")
    public void createPoll(@Valid @RequestBody Poll poll){
        pollService.createPoll(poll);
    }


//    Individual get retrieval
//    @RequestMapping(value = "/polls{pollId}", method = RequestMethod.GET)
//    public ResponseEntity<?> getPoll(@PathVariable Long pollId){
//        Optional<Poll> p = pollRepository.findById(pollId);
//        return new ResponseEntity<>(p, HttpStatus.OK);
//    }
    //Better Get Retrieval
    @GetMapping("/polls/{id}")
    public Optional<Poll> getById(@PathVariable Long id) {

            if (pollRepository.findById(id).isEmpty()) {
                throw new ResourceNotFoundException("Poll id with value of " + id + " does not exist, dummy.");
            } else {
                return pollService.getById(id);
            }
        }
    //update request
    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId){
        verifyPoll(pollId);
        Poll p = pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    @PutMapping("/polls/{id}")
//    public void updatePoll(@RequestBody Poll poll, @PathVariable Long pollId){
//        pollService.updatePoll(poll, pollId);
//    }

    //poopoo delete request
//    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
//    public ResponseEntity<?> deletePoll(@PathVariable Long pollId){
//        pollRepository.deleteById(pollId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    better delete request
    @DeleteMapping("/polls/{id}")
    public void deletePoll(@PathVariable Long id){
        verifyPoll(id);
        pollService.deletePoll(id);
    }



}
