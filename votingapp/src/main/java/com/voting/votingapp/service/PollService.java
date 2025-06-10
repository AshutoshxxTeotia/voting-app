package com.voting.votingapp.service;

import com.voting.votingapp.model.OptionVote;
import com.voting.votingapp.model.Poll;
import com.voting.votingapp.repository.PollRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PollService {

    private final PollRepository pollRepository;

    public Poll createPoll(Poll poll) {
        poll.setId(null);
        return pollRepository.save(poll);
    }
    public List<Poll> getAllPolls() {
     return pollRepository.findAll();
    }

    public Optional<Poll> getPollById(Long pollId) {
        return pollRepository.findById(pollId);
    }

    public void vote(long pollId, int optionIndex) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(()->new RuntimeException("Poll not found"));

            List<OptionVote> allOptions = poll.getOptions();
            if(optionIndex < 0 || optionIndex >= allOptions.size()){
                throw new IllegalArgumentException("Invalid option index");
            }
            OptionVote selectedOption = allOptions.get(optionIndex);
            selectedOption.setVoteCount(selectedOption.getVoteCount()+1);
            pollRepository.save(poll);
        }
}
