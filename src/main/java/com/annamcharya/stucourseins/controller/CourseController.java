package com.annamcharya.stucourseins.controller;

import com.annamcharya.stucourseins.entity.Course;
import com.annamcharya.stucourseins.exceptions.CourseNotFoundException;
import com.annamcharya.stucourseins.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        logger.info("Fetching all courses");
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId) {
        logger.info("Fetching course with ID: {}", courseId);
        try {
            Course course = courseService.getCourseById(courseId);
            return ResponseEntity.ok(course);
        } catch (CourseNotFoundException ex) {
            logger.error("Course with ID {} not found", courseId, ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        logger.info("Creating course: {}", course);
        Course createdCourse = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);

    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long courseId, @RequestBody Course courseDetails) {
        logger.info("Updating course with ID: {}", courseId);
        Course updatedCourse = courseService.updateCourse(courseId, courseDetails);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        logger.info("Deleting course with ID: {}", courseId);
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> handleCourseNotFoundException(CourseNotFoundException ex) {
        logger.error("Course not found", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
    }
}
