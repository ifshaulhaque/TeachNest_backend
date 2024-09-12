package com.example.TeachNest.services;

import com.example.TeachNest.entities.Student;
import com.example.TeachNest.entities.StudentAttendance;
import com.example.TeachNest.repositories.StudentAttendanceRepository;
import com.example.TeachNest.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public Student getStudentByUsername(String username) {
        return studentRepository.findById(username).get();
    }

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

    public StudentAttendance addStudentAttendance(StudentAttendance studentAttendance) {
        return studentAttendanceRepository.insert(studentAttendance);
    }

    public List<StudentAttendance> addStudentAttendanceList(List<StudentAttendance> studentAttendanceList) {
        return studentAttendanceRepository.insert(studentAttendanceList);
    }

    public Student addBatch(String studentUsername, List<String> batchId) {
        Student student = studentRepository.findById(studentUsername).get();
        if (student.getBatchesId() == null) {
            student.setBatchesId(new HashSet<>(){{ addAll(batchId); }});
        } else {
            student.getBatchesId().addAll(batchId);
        }
        return studentRepository.save(student);
    }

    public List<Student> getStudentFromBatch(String batchId) {
        return studentRepository.findByBatchesIdContaining(batchId);
    }
}
