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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{instituteUsername}")
    public ResponseEntity<ResponseModel> getTeachersByInstituteUsername(@PathVariable("instituteUsername") String instituteUsername) {
        return ResponseUtil.getResponse(
                new ResponseModel(
                        HttpStatus.OK,
                        "Successfully getting teacher list of "+instituteUsername+" institute",
                        teacherService.getTeacherByInstituteUsername(instituteUsername)
                )
        );
    }

    @GetMapping("/user-name-exist/{username}")
    public ResponseEntity<ResponseModel> isTeacherExists(@PathVariable String username) {
        HttpStatus httpStatus = teacherService.isTeacherExist(username);
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
