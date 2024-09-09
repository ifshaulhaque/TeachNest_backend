package com.example.TeachNest.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "batches")
public class StudentBatch {
    @Id
    private String id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Date batchTiming;
    private List<String> studentsUsername;
    private String instituteUsername;
    private String teacherUsername;
}
