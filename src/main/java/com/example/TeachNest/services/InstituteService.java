package com.example.TeachNest.services;

import com.example.TeachNest.entities.Institute;
import com.example.TeachNest.repositories.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InstituteService {

    @Autowired
    private InstituteRepository instituteRepository;

    public Institute registerInstitute(Institute institute) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        institute.setPassword(bCryptPasswordEncoder.encode(institute.getPassword()));
        return instituteRepository.insert(institute);
    }

    public HttpStatus isUserNameExist(String username) {
        boolean isExistUserName = instituteRepository.existsById(username);
        return isExistUserName ? HttpStatus.FOUND : HttpStatus.NOT_FOUND;
    }
}
