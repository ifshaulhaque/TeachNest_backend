package com.example.TeachNest.entities;

import com.example.TeachNest.enums.AttendanceStatus;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "attendances")
public class StudentAttendance {
    @Id
    private String Id;
    @NotBlank(message = "student username is a required field")
    private String studentUsername;
    private String instituteUsername;
    private String teacherUsername;
    @NotNull(message = "date is a required field")
    private Date date;
    @NotNull(message = "attendance status is a required field")
    private AttendanceStatus attendanceStatus;
}
