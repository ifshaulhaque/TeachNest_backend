package com.example.TeachNest.controllers;

import com.example.TeachNest.entities.StudentBatch;
import com.example.TeachNest.models.ResponseModel;
import com.example.TeachNest.services.BatchService;
import com.example.TeachNest.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/batch")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @PostMapping("/add")
    public ResponseEntity<ResponseModel> addBatch(@RequestBody StudentBatch studentBatch) {
        return ResponseUtil.getResponse(
                new ResponseModel(
                        HttpStatus.OK,
                        "Batch added successfully",
                        batchService.addBatch(studentBatch)
                )
        );
    }

    @GetMapping("{instituteUsername}")
    public ResponseEntity<ResponseModel> getBatchByInstituteUsername(@PathVariable("instituteUsername") String instituteUsername) {
        return ResponseUtil.getResponse(
                new ResponseModel(
                        HttpStatus.OK,
                        "successfully fetching batches by institute username",
                        batchService.getBatchesByInstituteUsername(instituteUsername)
                )
        );
    }

    @PostMapping("/student")
    public ResponseEntity<ResponseModel> addStudentInBatch(@RequestParam("batchId") String batchId, @RequestParam("studentUsername") String studentUsername) {
        return ResponseUtil.getResponse(
                new ResponseModel(
                        HttpStatus.OK,
                        "student added",
                        batchService.addStudentInBatch(batchId, studentUsername)
                )
        );
    }
}
