package com.annamcharya.stucourseins.controller;

import com.annamcharya.stucourseins.entity.Enrollment;
import com.annamcharya.stucourseins.exceptions.EnrollmentNotFoundException;
import com.annamcharya.stucourseins.service.EnrollmentService;
import com.annamcharya.stucourseins.service.Impl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentController.class);

    @Autowired
    private EnrollmentService enrollmentService;
    private EmailServiceImpl emailService;
    public EnrollmentController(EmailServiceImpl emailService) {
        this.enrollmentService = enrollmentService;
        this.emailService = emailService;
    }
    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        logger.info("Fetching all enrollments");
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long enrollmentId) {
        logger.info("Fetching enrollment with ID: {}", enrollmentId);
        try {
            Enrollment enrollment = enrollmentService.getEnrollmentById(enrollmentId);
            return ResponseEntity.ok(enrollment);
        } catch (EnrollmentNotFoundException ex) {
            logger.error("Enrollment with ID {} not found", enrollmentId, ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        logger.info("Creating enrollment: {}", enrollment);
        Enrollment createdEnrollment = enrollmentService.createEnrollment(enrollment);
        List<Enrollment> enrollmentMail=enrollmentService.getEnrollmentsWithEmail();
        String mail= String.valueOf(enrollmentMail.get(0));
        try {
            // Send enrollment confirmation email to student
            emailService.sendEnrollmentConfirmationEmail(mail, "student");
            // Send enrollment confirmation email to instructor
            emailService.sendEnrollmentConfirmationEmail(mail, "instructor");
        } catch (MessagingException e) {
            logger.error("Failed to send enrollment confirmation email.", e);
            // Handle the exception or return an appropriate response
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEnrollment);
    }

    @PutMapping("/{enrollmentId}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable Long enrollmentId, @RequestBody Enrollment enrollmentDetails) {
        logger.info("Updating enrollment with ID: {}", enrollmentId);
        Enrollment updatedEnrollment = enrollmentService.updateEnrollment(enrollmentId, enrollmentDetails);
        return ResponseEntity.ok(updatedEnrollment);
    }

    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long enrollmentId) {
        logger.info("Deleting enrollment with ID: {}", enrollmentId);
        enrollmentService.deleteEnrollment(enrollmentId);
        return ResponseEntity.noContent().build();
    }

    // Exception handler for handling custom exceptions
    @ExceptionHandler(EnrollmentNotFoundException.class)
    public ResponseEntity<String> handleEnrollmentNotFoundException(EnrollmentNotFoundException ex) {
        logger.error("Enrollment not found", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enrollment not found");
    }
    @GetMapping("/enrollmentsWithEmail")
    public ResponseEntity<List<Enrollment>> getEnrollmentsWithEmail() {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsWithEmail();
        return ResponseEntity.ok().body(enrollments);
    }
}
