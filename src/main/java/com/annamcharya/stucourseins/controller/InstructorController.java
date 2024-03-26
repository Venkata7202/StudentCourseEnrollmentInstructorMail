package com.annamcharya.stucourseins.controller;

import com.annamcharya.stucourseins.entity.Instructor;
import com.annamcharya.stucourseins.exceptions.InstructorNotFoundException;
import com.annamcharya.stucourseins.service.InstructorService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private static final Logger logger = LoggerFactory.getLogger(InstructorController.class);

    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        logger.info("Fetching all instructors");
        List<Instructor> instructors = instructorService.getAllInstructors();
        return ResponseEntity.ok(instructors);
    }

    @GetMapping("/{instructorId}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Long instructorId) {
        logger.info("Fetching instructor with ID: {}", instructorId);
        try {
            Instructor instructor = instructorService.getInstructorById(instructorId);
            return ResponseEntity.ok(instructor);
        } catch (InstructorNotFoundException ex) {
            logger.error("Instructor with ID {} not found", instructorId, ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@Valid @RequestBody Instructor instructor) {
        Instructor createdInstructor = instructorService.createInstructor(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInstructor);
    }

    // Update an existing instructor
    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @Valid @RequestBody Instructor instructorDetails) {
        Instructor updatedInstructor = instructorService.updateInstructor(id, instructorDetails);
        if (updatedInstructor != null) {
            return ResponseEntity.ok(updatedInstructor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an instructor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }
}


