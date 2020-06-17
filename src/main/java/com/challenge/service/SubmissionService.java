package com.challenge.service;

import java.math.BigDecimal;
import java.util.List;

import com.challenge.entity.Submission;
import com.challenge.repository.AccelerationRepository;
import com.challenge.repository.ChallengeRepository;
import com.challenge.repository.SubmissionRepository;
import com.challenge.service.interfaces.SubmissionServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionService implements SubmissionServiceInterface {

    @Autowired
    private SubmissionRepository repository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private AccelerationRepository accelerationRepository;

    @Override
    public Submission save(Submission object) {
        return this.repository.save(object);
    }

    @Override
    public BigDecimal findHigherScoreByChallengeId(Long challengeId) {
        List<Submission> challengeSubmissions = challengeRepository.findById(challengeId).get().getSubmissions();
        Float maxScore = 0F;
        for (Submission s : challengeSubmissions){
            if (s.getScore() > maxScore)
                maxScore = s.getScore();
        }
        return BigDecimal.valueOf(maxScore);
    }

    @Override
    public List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId) {
        return accelerationRepository.findById(accelerationId).get().getChallenge().getSubmissions();
    }
    

}