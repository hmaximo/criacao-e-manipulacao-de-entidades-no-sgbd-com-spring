package com.challenge.repository;

import com.challenge.entity.Submission;
import com.challenge.entity.SubmissionId;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends CrudRepository<Submission, SubmissionId>{}