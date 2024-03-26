package com.annamcharya.stucourseins.repository;

import com.annamcharya.stucourseins.entity.Enrollment;
import com.annamcharya.stucourseins.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    @Query("SELECT e FROM Enrollment e JOIN FETCH e.student JOIN FETCH e.course")
    List<Enrollment> findEnrollmentsWithEmail();
    // You can define custom query methods if needed

}
