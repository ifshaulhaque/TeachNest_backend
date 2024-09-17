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

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/api/student")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{username}")
    public ResponseEntity<ResponseModel> getStudentByUsername(@PathVariable("username") String username) {
        return ResponseUtil.getResponse(
                new ResponseModel(
                        HttpStatus.OK,
                        "student fetched",
                        studentService.getStudentByUsername(username)
                )
        );
    }

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

    @GetMapping("/")
    public ResponseEntity<ResponseModel> getStudentsByInstituteUsername(@RequestParam("instituteUsername") String instituteUsername) {
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

    @GetMapping("/attendances")
    public ResponseEntity<ResponseModel> gettingStudentAttendance(@RequestParam("batchId") String batchId, @RequestParam("date") Date date) {
        return ResponseUtil.getResponse(
                new ResponseModel(
                        HttpStatus.OK,
                        "student attendence fetched for date: " + date + ";",
                        studentService.getStudentAttendance(batchId, date)
                )
        );
    }

    @PatchMapping("/batch")
    public ResponseEntity<ResponseModel> updateBatch(@RequestParam("studentUsername") String studentUsername, @RequestParam("batchesId") List<String> batchesId) {
        return ResponseUtil.getResponse(
                new ResponseModel(
                        HttpStatus.OK,
                        "batch added",
                        studentService.updateBatch(studentUsername, batchesId)
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
