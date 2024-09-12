package com.example.TeachNest.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;

@Data
@Document(collection = "students")
public class Student {
    @Id
    private String username;
    private String name;
    private String emailId;
    private String mobileNo;
    @NotBlank(message = "Password is required")
    private String password;
    private String instituteUsername;
    private HashSet<String> batchesId;
}
