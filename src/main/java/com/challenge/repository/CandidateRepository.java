package com.challenge.repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, CandidateId>{}