package com.example.TeachNest.services;

import com.example.TeachNest.entities.Student;
import com.example.TeachNest.entities.StudentAttendance;
import com.example.TeachNest.enums.AttendanceStatus;
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

    public List<StudentAttendance> addStudentAttendanceList(List<StudentAttendance> studentAttendanceList) {
        List<StudentAttendance> presentAttendance = new ArrayList<>();
        List<StudentAttendance> absentAttendance = new ArrayList<>();

        for (StudentAttendance studentAttendance: studentAttendanceList) {
            if (studentAttendance.getAttendanceStatus().equals(AttendanceStatus.PRESENT)) {
                presentAttendance.add(studentAttendance);
            } else {
                absentAttendance.add(studentAttendance);
            }
        }
        studentAttendanceRepository.deleteAll(absentAttendance);
        return studentAttendanceRepository.saveAll(presentAttendance);
    }

    public Student updateBatch(String studentUsername, List<String> batchId) {
        Student student = studentRepository.findById(studentUsername).get();
        student.setBatchesId(new HashSet<>(){{ addAll(batchId); }});
        return studentRepository.save(student);
    }

    public List<Student> getStudentFromBatch(String batchId) {
        return studentRepository.findByBatchesIdContaining(batchId);
    }

    public List<StudentAttendance> getStudentAttendance(String batchId, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startOfDay = calendar.getTime();

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endOfDay = calendar.getTime();

        return studentAttendanceRepository.findByBatchIdAndDateRange(batchId, startOfDay, endOfDay);
    }

    public List<StudentAttendance> getStudentAttendanceOfInstitute(String instituteUsername) {
        return studentAttendanceRepository.findByInstituteUsername(instituteUsername);
    }
}
