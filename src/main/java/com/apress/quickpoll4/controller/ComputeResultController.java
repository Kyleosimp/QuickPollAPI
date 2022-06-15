package com.apress.quickpoll4.controller;

import com.apress.quickpoll4.domain.Vote;
import com.apress.quickpoll4.dto.OptionCount;
import com.apress.quickpoll4.dto.VoteResult;
import com.apress.quickpoll4.repository.VoteRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ComputeResultController {
    @Autowired
    private VoteRepository voteRepository;

    @RequestMapping(value="/computeresult/{pollId}", method=RequestMethod.GET)
    public ResponseEntity<?> computeResult(@PathVariable Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);

        // Algorithm to count votes
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
        for(Vote v : allVotes) {
            totalVotes ++;
            // Get the OptionCount corresponding to this Option
            OptionCount optionCount = tempMap.get(v.getOption().getId());
            if(optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount()+1);
        }

        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());

        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }


}
