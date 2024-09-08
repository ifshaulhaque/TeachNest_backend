package com.example.TeachNest.services;

import com.example.TeachNest.entities.Student;
import com.example.TeachNest.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public Student registerStudent(Student student) {
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        return studentRepository.insert(student);
    }

    public List<Student> getStudentByInstituteUsername(String instituteUsername) {
        return studentRepository.findByInstituteUsername(instituteUsername);
    }

    public HttpStatus isStudentExist(String username) {
        boolean isTeacherExist = studentRepository.existsById(username);
        return isTeacherExist ? HttpStatus.FOUND : HttpStatus.NOT_FOUND;
    }
}
