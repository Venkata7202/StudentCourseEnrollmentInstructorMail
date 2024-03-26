package com.annamcharya.stucourseins.service.Impl;

import com.annamcharya.stucourseins.entity.Course;
import com.annamcharya.stucourseins.exceptions.CourseNotFoundException;
import com.annamcharya.stucourseins.repository.CourseRepository;
import com.annamcharya.stucourseins.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        logger.info("Fetching all courses");
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long courseId) {
        logger.info("Fetching course with ID: {}", courseId);
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            return optionalCourse.get();
        } else {
            logger.error("Course with ID {} not found", courseId);
            throw new CourseNotFoundException("Course with ID " + courseId + " not found");
        }
    }

    @Override
    public Course createCourse(Course course) {
        logger.info("Creating course: {}", course);
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long courseId, Course courseDetails) {
        logger.info("Updating course with ID: {}", courseId);
        Course existingCourse = getCourseById(courseId);
        existingCourse.setCourseName(courseDetails.getCourseName());
        existingCourse.setCourseDescription(courseDetails.getCourseDescription());
        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long courseId) {
        logger.info("Deleting course with ID: {}", courseId);
        Course existingCourse = getCourseById(courseId);
        courseRepository.delete(existingCourse);
    }
}
