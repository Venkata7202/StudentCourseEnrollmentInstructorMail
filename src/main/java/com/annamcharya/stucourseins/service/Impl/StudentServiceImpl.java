package com.annamcharya.stucourseins.service.Impl;

import com.annamcharya.stucourseins.entity.Student;
import com.annamcharya.stucourseins.exceptions.StudentNotFoundException;
import com.annamcharya.stucourseins.repository.StudentRepository;
import com.annamcharya.stucourseins.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        logger.info("Fetching all students");
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long studentId) {
        logger.info("Fetching student with ID: {}", studentId);
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else {
            logger.error("Student with ID {} not found", studentId);
            throw new StudentNotFoundException("Student with ID " + studentId + " not found");
        }
    }

    @Override
    public Student createStudent(Student student) {
        logger.info("Creating student: {}", student);
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long studentId, Student studentDetails) {
        logger.info("Updating student with ID: {}", studentId);
        Student existingStudent = getStudentById(studentId);
        existingStudent.setFirstName(studentDetails.getFirstName());
        existingStudent.setLastName(studentDetails.getLastName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setPhoneNumber(studentDetails.getPhoneNumber());
        existingStudent.setAddress(studentDetails.getAddress());
        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long studentId) {
        logger.info("Deleting student with ID: {}", studentId);
        Student existingStudent = getStudentById(studentId);
        studentRepository.delete(existingStudent);
    }
}
