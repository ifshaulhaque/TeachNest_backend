package com.example.TeachNest.repositories;

import com.example.TeachNest.entities.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeacherRepository extends MongoRepository<Teacher, String> {
    List<Teacher> findByInstituteUsername(String instituteUsername);
}
