package com.annamcharya.stucourseins.service.Impl;

import com.annamcharya.stucourseins.entity.Instructor;
import com.annamcharya.stucourseins.exceptions.InstructorNotFoundException;
import com.annamcharya.stucourseins.repository.InstructorRepository;
import com.annamcharya.stucourseins.service.InstructorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstructorServiceImpl implements InstructorService {

    private static final Logger logger = LoggerFactory.getLogger(InstructorServiceImpl.class);

    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public List<Instructor> getAllInstructors() {
        logger.info("Fetching all instructors");
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getInstructorById(Long instructorId) {
        logger.info("Fetching instructor with ID: {}", instructorId);
        Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorId);
        if (optionalInstructor.isPresent()) {
            return optionalInstructor.get();
        } else {
            logger.error("Instructor with ID {} not found", instructorId);
            throw new InstructorNotFoundException("Instructor with ID " + instructorId + " not found");
        }
    }

    @Override
    public Instructor createInstructor(Instructor instructor) {
        logger.info("Creating instructor: {}", instructor);
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor updateInstructor(Long instructorId, Instructor instructorDetails) {
        logger.info("Updating instructor with ID: {}", instructorId);
        Instructor existingInstructor = getInstructorById(instructorId);
        existingInstructor.setFirstName(instructorDetails.getFirstName());
        existingInstructor.setLastName(instructorDetails.getLastName());
        existingInstructor.setEmail(instructorDetails.getEmail());
        return instructorRepository.save(existingInstructor);
    }

    @Override
    public void deleteInstructor(Long instructorId) {
        logger.info("Deleting instructor with ID: {}", instructorId);
        Instructor existingInstructor = getInstructorById(instructorId);
        instructorRepository.delete(existingInstructor);
    }
}

