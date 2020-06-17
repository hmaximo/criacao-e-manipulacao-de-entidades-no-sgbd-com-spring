package com.challenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.challenge.entity.Candidate;
import com.challenge.entity.Company;
import com.challenge.repository.AccelerationRepository;
import com.challenge.repository.CompanyRepository;
import com.challenge.repository.UserRepository;
import com.challenge.service.interfaces.CompanyServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService implements CompanyServiceInterface {

    @Autowired
    private CompanyRepository repository;
    
    @Autowired
    private AccelerationRepository accelerationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Company save(Company object) {
        return this.repository.save(object);
    }

    @Override
    public Optional<Company> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public List<Company> findByAccelerationId(Long accelerationId) {
        List<Candidate> accelerationCandidates = accelerationRepository.findById(accelerationId).get().getCandidates();
        List<Company> accelerationCompanies = new ArrayList<>();
        for (Candidate c : accelerationCandidates){
            if (!accelerationCompanies.contains(c.getId().getCompany()))
                accelerationCompanies.add(c.getId().getCompany());
        }
        return accelerationCompanies;
    }

    @Override
    public List<Company> findByUserId(Long userId) {    
        List<Candidate> userCandidacies = userRepository.findById(userId).get().getCandidates();
        List<Company> userCompanies = new ArrayList<>();
        for (Candidate c : userCandidacies)
            userCompanies.add(c.getId().getCompany());
        return userCompanies;
    }
    
}