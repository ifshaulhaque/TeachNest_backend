package com.example.TeachNest.controllers;

import com.example.TeachNest.entities.Teacher;
import com.example.TeachNest.models.ResponseModel;
import com.example.TeachNest.services.TeacherService;
import com.example.TeachNest.utils.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/teacher")
@Validated
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/register")
    public ResponseEntity<ResponseModel> registerTeacher(@Valid @RequestBody Teacher teacher) {
        return ResponseUtil.getResponse(
                new ResponseModel(
                        HttpStatus.OK,
                        "Teacher Registered Successfully",
                        teacherService.registerTeacher(teacher)
                )
        );
    }
}
