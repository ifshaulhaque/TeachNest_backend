package com.example.TeachNest.repositories;

import com.example.TeachNest.entities.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<Teacher, String> {
}
