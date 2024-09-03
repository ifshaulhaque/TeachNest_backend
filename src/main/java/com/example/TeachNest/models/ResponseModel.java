package com.example.TeachNest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ResponseModel {
    private HttpStatus httpStatus;
    private String statusMessage;
    private Object data;
}
