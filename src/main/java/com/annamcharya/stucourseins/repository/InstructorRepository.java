package com.annamcharya.stucourseins.repository;

import com.annamcharya.stucourseins.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    // You can define custom query methods if needed
}
