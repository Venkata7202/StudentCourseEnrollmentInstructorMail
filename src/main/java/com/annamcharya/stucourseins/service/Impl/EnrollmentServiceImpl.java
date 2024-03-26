package com.annamcharya.stucourseins.service.Impl;

import com.annamcharya.stucourseins.entity.Enrollment;
import com.annamcharya.stucourseins.entity.Instructor;
import com.annamcharya.stucourseins.exceptions.EnrollmentNotFoundException;
import com.annamcharya.stucourseins.repository.EnrollmentRepository;
import com.annamcharya.stucourseins.service.EnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentServiceImpl.class);

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private EmailServiceImpl emailService;

    @Override
    public List<Enrollment> getAllEnrollments() {
        logger.info("Fetching all enrollments");
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment getEnrollmentById(Long enrollmentId) {
        logger.info("Fetching enrollment with ID: {}", enrollmentId);
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(enrollmentId);
        if (optionalEnrollment.isPresent()) {
            return optionalEnrollment.get();
        } else {
            logger.error("Enrollment with ID {} not found", enrollmentId);
            throw new EnrollmentNotFoundException("Enrollment with ID " + enrollmentId + " not found");
        }
    }

    @Override
    public Enrollment createEnrollment(Enrollment enrollment) {
        logger.info("Creating enrollment: {}", enrollment);
        Enrollment createdEnrollment = enrollmentRepository.save(enrollment);
        return createdEnrollment;
    }

    @Override
    public Enrollment updateEnrollment(Long enrollmentId, Enrollment enrollmentDetails) {
        logger.info("Updating enrollment with ID: {}", enrollmentId);
        Enrollment existingEnrollment = getEnrollmentById(enrollmentId);
        existingEnrollment.setStudent(enrollmentDetails.getStudent());
        existingEnrollment.setCourse(enrollmentDetails.getCourse());
        existingEnrollment.setEnrollmentDate(enrollmentDetails.getEnrollmentDate());
        return enrollmentRepository.save(existingEnrollment);
    }

    @Override
    public void deleteEnrollment(Long enrollmentId) {
        logger.info("Deleting enrollment with ID: {}", enrollmentId);
        Enrollment existingEnrollment = getEnrollmentById(enrollmentId);
        enrollmentRepository.delete(existingEnrollment);
    }
    public List<Enrollment> getEnrollmentsWithEmail() {
        return enrollmentRepository.findEnrollmentsWithEmail();
    }

}

