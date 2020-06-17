package com.challenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.challenge.entity.Candidate;
import com.challenge.entity.User;
import com.challenge.repository.AccelerationRepository;
import com.challenge.repository.CompanyRepository;
import com.challenge.repository.UserRepository;
import com.challenge.service.interfaces.UserServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import static java.time.LocalDateTime.now;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AccelerationRepository accelerationRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public User save(User object) {
        object.setCreatedAt(now());
        return this.repository.save(object);
    }

    @Override
    public Optional<User> findById(Long userId){
        return this.repository.findById(userId);
    }

    public List<User> findByAccelerationName(String name){
        List<Candidate> accelerationCandidates = accelerationRepository.findByName(name).get().getCandidates();
        List<User> accelerationUsers = new ArrayList<>();
        for (Candidate c : accelerationCandidates)
            accelerationUsers.add(c.getId().getUser());
        return accelerationUsers;
    }

    @Override
    public List<User> findByCompanyId(Long companyId) {
        List<Candidate> companyCandidates = companyRepository.findById(companyId).get().getCandidates();
        List<User> companyUsers = new ArrayList<>();
        for (Candidate c : companyCandidates)
            companyUsers.add(c.getId().getUser());
        return companyUsers;
    }
}