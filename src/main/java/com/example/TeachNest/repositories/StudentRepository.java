package com.example.TeachNest.repositories;

import com.example.TeachNest.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {
    List<Student> findByInstituteUsername(String instituteUsername);
}
