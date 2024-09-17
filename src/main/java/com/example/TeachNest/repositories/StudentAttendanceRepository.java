package com.example.TeachNest.repositories;

import com.example.TeachNest.entities.StudentAttendance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface StudentAttendanceRepository extends MongoRepository<StudentAttendance, String> {
    List<StudentAttendance> findByBatchIdAndDate(String batchId, Date date);
}
