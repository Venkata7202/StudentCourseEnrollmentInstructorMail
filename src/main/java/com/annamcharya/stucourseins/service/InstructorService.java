package com.annamcharya.stucourseins.service;

import com.annamcharya.stucourseins.entity.Instructor;

import java.util.List;
import java.util.UUID;

public interface InstructorService {
    List<Instructor> getAllInstructors();
    Instructor getInstructorById(Long instructorId);
    Instructor createInstructor(Instructor instructor);
    Instructor updateInstructor(Long instructorId, Instructor instructorDetails);
    void deleteInstructor(Long instructorId);
}
