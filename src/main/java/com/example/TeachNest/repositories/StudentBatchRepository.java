package com.example.TeachNest.repositories;

import com.example.TeachNest.entities.StudentBatch;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentBatchRepository extends MongoRepository<StudentBatch, String> {
    List<StudentBatch> findByInstituteUsername(String instituteUsername);
}
