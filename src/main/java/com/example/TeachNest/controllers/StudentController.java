package com.example.TeachNest.controllers;

import com.example.TeachNest.entities.Student;
import com.example.TeachNest.entities.StudentAttendance;
import com.example.TeachNest.models.ResponseModel;
import com.example.TeachNest.services.StudentService;
import com.example.TeachNest.utils.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/attendance")
    public ResponseEntity<ResponseModel> addStudentAttendance(@Valid @RequestBody StudentAttendance studentAttendance) {
        return ResponseUtil.getResponse(
                new ResponseModel(
                        HttpStatus.OK,
                        "Attendance marked successfully",
                        studentService.addStudentAttendance(studentAttendance)
                )
        );
    }

    @PostMapping("/attendances")
    public ResponseEntity<ResponseModel> addStudentAttendanceList(@Valid @RequestBody List<StudentAttendance> studentAttendanceList) {
        return ResponseUtil.getResponse(
                new ResponseModel(
                        HttpStatus.OK,
                        "Attendances marked successfully",
                        studentService.addStudentAttendanceList(studentAttendanceList)
                )
        );
    }

    @PatchMapping
    public ResponseEntity<ResponseModel> addBatch(@RequestParam("studentUsername") String studentUsername, @RequestParam("batchId") String batchId) {
        return ResponseUtil.getResponse(
                new ResponseModel(
                        HttpStatus.OK,
                        "batch added",
                        studentService.addBatch(studentUsername, batchId)
                )
        );
    }

    @GetMapping("/batch/{batchId}")
    public ResponseEntity<ResponseModel> getStudentFromBatch(@PathVariable("batchId") String batchId) {
        return ResponseUtil.getResponse(
                new ResponseModel(
                        HttpStatus.OK,
                        "Succefully fetch students from batchId : " + batchId,
                        studentService.getStudentFromBatch(batchId)
                )
        );
    }
}
