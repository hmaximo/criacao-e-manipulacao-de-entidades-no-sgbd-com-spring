package com.challenge.service;

import java.util.ArrayList;
import java.util.List;

import com.challenge.entity.Challenge;
import com.challenge.entity.Submission;
import com.challenge.repository.AccelerationRepository;
import com.challenge.repository.ChallengeRepository;
import com.challenge.repository.UserRepository;
import com.challenge.service.interfaces.ChallengeServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService implements ChallengeServiceInterface {

    @Autowired
    private ChallengeRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccelerationRepository accelerationRepository;

    @Override
    public Challenge save(Challenge object) {
        return this.repository.save(object);
    }

    @Override
    public List<Challenge> findByAccelerationIdAndUserId(Long accelerationId, Long userId) {
        List<Submission> userSubmissions = userRepository.findById(userId).get().getSubmissions();
        Challenge challenge = accelerationRepository.findById(accelerationId).get().getChallenge();
        List<Challenge> challenges = new ArrayList<>();
        for (Submission s : userSubmissions)
            if (s.getId().getChallenge().equals(challenge))
                challenges.add(s.getId().getChallenge());
        return challenges;
    }
    
}