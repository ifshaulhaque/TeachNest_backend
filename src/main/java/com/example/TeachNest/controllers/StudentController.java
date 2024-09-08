package com.example.TeachNest.controllers;

import com.example.TeachNest.entities.Student;
import com.example.TeachNest.models.ResponseModel;
import com.example.TeachNest.services.StudentService;
import com.example.TeachNest.utils.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/student")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<ResponseModel> registerStudent(@Valid @RequestBody Student student) {
        return ResponseUtil.getResponse(
                new ResponseModel(
                        HttpStatus.OK,
                        "Student Registered Successfully",
                        studentService.registerStudent(student)
                )
        );
    }

    @GetMapping("/{instituteUsername}")
    public ResponseEntity<ResponseModel> getStudentsByInstituteUsername(@PathVariable("instituteUsername") String instituteUsername) {
        return ResponseUtil.getResponse(
                new ResponseModel(
                        HttpStatus.OK,
                        "Successfully getting student list of "+instituteUsername+" institute",
                        studentService.getStudentByInstituteUsername(instituteUsername)
                )
        );
    }

    @GetMapping("/user-name-exist/{username}")
    public ResponseEntity<ResponseModel> isStudentExists(@PathVariable String username) {
        HttpStatus httpStatus = studentService.isStudentExist(username);
        if (httpStatus == HttpStatus.FOUND) {
            return ResponseUtil.getResponse(
                    new ResponseModel(
                            HttpStatus.FOUND,
                            "",
                            ""
                    )
            );
        } else {
            return ResponseUtil.getResponse(
                    new ResponseModel(
                            HttpStatus.NOT_FOUND,
                            "",
                            ""
                    )
            );
        }
    }
}
