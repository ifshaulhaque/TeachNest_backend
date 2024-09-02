package com.example.TeachNest.controllers;

import com.example.TeachNest.entities.Institute;
import com.example.TeachNest.services.InstituteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/institute")
@Validated
public class InstituteController {

    @Autowired
    private InstituteService instituteService;

    @PostMapping("/register")
    public ResponseEntity<Institute> registerInstitute(@Valid @RequestBody Institute institute) {
        Institute registerInstitute = instituteService.registerInstitute(institute);
        return new ResponseEntity<>(registerInstitute, HttpStatus.CREATED);
    }

    @GetMapping("/user-name-exist/{username}")
    public ResponseEntity<String> isUserNameExist(@PathVariable String username) {
        return ResponseEntity.status(instituteService.isUserNameExist(username)).body("");
    }
}
