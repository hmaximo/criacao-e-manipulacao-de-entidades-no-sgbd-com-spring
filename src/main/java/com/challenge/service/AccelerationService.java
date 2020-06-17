package com.challenge.service;

import java.util.List;
import java.util.Optional;

import com.challenge.entity.Acceleration;
import com.challenge.repository.AccelerationRepository;
import com.challenge.service.interfaces.AccelerationServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccelerationService implements AccelerationServiceInterface {

    @Autowired
    private AccelerationRepository repository;

    @Override
    public Acceleration save(Acceleration object) {
        return this.repository.save(object);
    }

    @Override
    public Optional<Acceleration> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public List<Acceleration> findByCompanyId(Long companyId) {
        /*List<Candidate> companyCandidates = companyRepository.findById(companyId).get().getCandidates();
        List<Acceleration> companyAccelerations = new ArrayList<>();
        for (Candidate c : companyCandidates){
            if (!companyAccelerations.contains(c.getId().getAcceleration()))
                companyAccelerations.add(c.getId().getAcceleration());
        }*/
        return this.repository.findByCandidatesIdCompanyId(companyId);
    }

    @Override
    public Optional<Acceleration> findAccelerationByName(String name) {
        return this.repository.findByName(name);
    }
}