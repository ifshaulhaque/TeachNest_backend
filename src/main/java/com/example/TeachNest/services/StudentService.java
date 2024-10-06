package com.example.TeachNest.services;

import com.example.TeachNest.entities.Student;
import com.example.TeachNest.entities.StudentAttendance;
import com.example.TeachNest.enums.AttendanceStatus;
import com.example.TeachNest.repositories.StudentAttendanceRepository;
import com.example.TeachNest.repositories.StudentRepository;
import com.example.TeachNest.utils.DateUtils;
import com.example.TeachNest.utils.IdUtils;
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

    public StudentAttendance addStudentAttendanceList(StudentAttendance studentAttendance) {
        studentAttendance.setId(IdUtils.getAttendanceId(studentAttendance));
        return studentAttendanceRepository.save(studentAttendance);
    }

    public Student updateBatch(String studentUsername, List<String> batchId) {
        Student student = studentRepository.findById(studentUsername).get();
        student.setBatchesId(new HashSet<>(){{ addAll(batchId); }});
        return studentRepository.save(student);
    }

    public List<Student> getStudentFromBatch(String batchId) {
        return studentRepository.findByBatchesIdContaining(batchId);
    }

    public StudentAttendance getStudentAttendance(Date date, String batchId) {
        return studentAttendanceRepository.findById(IdUtils.getAttendanceId(date, batchId)).get();
    }

    public List<StudentAttendance> getStudentAttendanceOfInstitute(String instituteUsername) {
        return studentAttendanceRepository.findByInstituteUsername(instituteUsername);
    }
}
