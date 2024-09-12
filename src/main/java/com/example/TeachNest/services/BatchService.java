package com.example.TeachNest.services;

import com.example.TeachNest.entities.StudentBatch;
import com.example.TeachNest.repositories.StudentBatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchService {

    @Autowired
    private StudentBatchRepository studentBatchRepository;

    public List<StudentBatch> getBatches(List<String> batchesId) {
        return studentBatchRepository.findAllById(batchesId);
    }

    public StudentBatch addBatch(StudentBatch studentBatch) {
        return studentBatchRepository.insert(studentBatch);
    }

    public List<StudentBatch> getBatchesByInstituteUsername(String instituteUsername) {
        return studentBatchRepository.findByInstituteUsername(instituteUsername);
    }

    public StudentBatch addStudentInBatch(String batchId, String username) {
        StudentBatch studentBatch = studentBatchRepository.findById(batchId).get();
        studentBatch.getStudentsUsername().add(username);
        return studentBatchRepository.save(studentBatch);
    }
}
