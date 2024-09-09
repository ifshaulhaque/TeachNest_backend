package com.example.TeachNest.repositories;

import com.example.TeachNest.entities.StudentAttendance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentAttendanceRepository extends MongoRepository<StudentAttendance, String> {
}
