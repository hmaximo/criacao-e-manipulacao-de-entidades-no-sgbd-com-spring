package com.challenge.service;

import java.util.List;
import java.util.Optional;

import com.challenge.entity.Acceleration;
import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import com.challenge.entity.Company;
import com.challenge.entity.User;
import com.challenge.repository.AccelerationRepository;
import com.challenge.repository.CandidateRepository;
import com.challenge.repository.CompanyRepository;
import com.challenge.repository.UserRepository;
import com.challenge.service.interfaces.CandidateServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService implements CandidateServiceInterface {

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private AccelerationRepository accelerationRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Candidate save(Candidate object) {
        return this.repository.save(object);
    }

    @Override
    public Optional<Candidate> findById(CandidateId id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<Candidate> findById(Long userId, Long companyId, Long accelerationId) {
        User user = userRepository.getOne(userId);
        Acceleration acceleration = accelerationRepository.getOne(accelerationId);
        Company company = companyRepository.getOne(companyId);
        return repository.findById(new CandidateId(user, acceleration, company));
    }

    @Override
    public List<Candidate> findByCompanyId(Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        return company.get().getCandidates();
    }

    @Override
    public List<Candidate> findByAccelerationId(Long accelerationId) {
        Optional<Acceleration> acceleration = accelerationRepository.findById(accelerationId);
        return acceleration.get().getCandidates();
    }
    
}