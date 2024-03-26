package com.annamcharya.stucourseins.service;

import com.annamcharya.stucourseins.entity.Course;


import java.util.List;
;
public interface CourseService {
        List<Course> getAllCourses();
        Course getCourseById(Long courseId);
        Course createCourse(Course course);
        Course updateCourse(Long

 courseId, Course courseDetails);
        void deleteCourse(Long courseId);
}



