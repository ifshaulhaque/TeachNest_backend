package com.example.TeachNest.repositories;

import com.example.TeachNest.entities.StudentAttendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface StudentAttendanceRepository extends MongoRepository<StudentAttendance, String> {
    @Query("{ 'batchId': ?0, 'date': { $gte: ?1, $lt: ?2 } }")
    List<StudentAttendance> findByBatchIdAndDateRange(String batchId, Date startOfDay, Date endOfDay);

    List<StudentAttendance> findByInstituteUsername(String instituteUsername);
}
