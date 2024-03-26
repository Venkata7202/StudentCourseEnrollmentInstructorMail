package com.annamcharya.stucourseins.controller;

import com.annamcharya.stucourseins.entity.Student;
import com.annamcharya.stucourseins.exceptions.StudentNotFoundException;
import com.annamcharya.stucourseins.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        logger.info("Fetching all students");
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        logger.info("Fetching student with ID: {}", studentId);
        try {
            Student student = studentService.getStudentById(studentId);
            return ResponseEntity.ok(student);
        } catch (StudentNotFoundException ex) {
            logger.error("Student with ID {} not found", studentId, ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        logger.info("Creating student: {}", student);
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student studentDetails) {
        logger.info("Updating student with ID: {}", studentId);
        Student updatedStudent = studentService.updateStudent(studentId, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        logger.info("Deleting student with ID: {}", studentId);
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException ex) {
        logger.error("Student not found", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
    }
}
