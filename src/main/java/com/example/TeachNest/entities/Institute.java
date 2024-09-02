package com.example.TeachNest.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "institutes")
public class Institute {
    @Id
    private String username;
    private String emailId;
    private String mobileNo;
    @NotBlank(message = "Password is required")
    private String password;
}
