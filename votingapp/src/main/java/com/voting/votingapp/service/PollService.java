package com.voting.votingapp.service;

import com.voting.votingapp.model.Poll;
import com.voting.votingapp.repository.PollRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PollService {

    private final PollRepository pollRepository;

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }
}
