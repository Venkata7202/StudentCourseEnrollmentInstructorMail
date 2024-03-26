package com.annamcharya.stucourseins.repository;

import com.annamcharya.stucourseins.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // You can define custom query methods if needed
}
