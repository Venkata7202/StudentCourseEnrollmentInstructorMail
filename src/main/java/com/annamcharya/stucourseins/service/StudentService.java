package com.annamcharya.stucourseins.service;

import com.annamcharya.stucourseins.entity.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long studentId);
    Student createStudent(Student student);
    Student updateStudent(Long studentId, Student studentDetails);
    void deleteStudent(Long studentId);
}
