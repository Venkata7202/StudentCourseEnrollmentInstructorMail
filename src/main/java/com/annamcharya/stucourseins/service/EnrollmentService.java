package com.annamcharya.stucourseins.service;

import com.annamcharya.stucourseins.entity.Enrollment;

import java.util.List;
import java.util.UUID;

public interface EnrollmentService {
    List<Enrollment> getAllEnrollments();
    Enrollment getEnrollmentById(Long enrollmentId);
    Enrollment createEnrollment(Enrollment enrollment);
    Enrollment updateEnrollment(Long enrollmentId, Enrollment enrollmentDetails);
    void deleteEnrollment(Long enrollmentId);

    List<Enrollment> getEnrollmentsWithEmail();
}
