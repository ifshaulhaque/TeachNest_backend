package com.example.TeachNest.services;

import com.example.TeachNest.entities.Teacher;
import com.example.TeachNest.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public Teacher registerTeacher(Teacher teacher) {
        teacher.setPassword(bCryptPasswordEncoder.encode(teacher.getPassword()));
        return teacherRepository.insert(teacher);
    }

    public List<Teacher> getTeacherByInstituteUsername(String instituteUsername) {
        return teacherRepository.findByInstituteUsername(instituteUsername);
    }
}
