package com.example.TeachNest.entities;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;

@Data
@Document(collection = "attendances")
public class StudentAttendance {
    @Id
    private String id;
    private String instituteUsername;
    private String teacherUsername;
    @NotNull(message = "date is a required field")
    private Date date;
    @NotNull(message = "batch is required")
    private String batchId;
    private HashSet<String> presentList = new HashSet<>();
}
