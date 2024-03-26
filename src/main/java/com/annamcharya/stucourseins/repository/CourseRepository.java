package com.annamcharya.stucourseins.repository;

import com.annamcharya.stucourseins.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // You can define custom query methods if needed
}
