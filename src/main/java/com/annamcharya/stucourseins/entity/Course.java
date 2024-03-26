package com.annamcharya.stucourseins.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id")
    private Long courseID;

    @NotEmpty(message = "Course name is required")
    private String courseName;

    @NotEmpty(message = "Course description is required")
    private String courseDescription;

    // You can include other relevant attributes and relationships here

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;

    @ManyToMany
    @JoinTable(
            name = "course_instructors",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private Set<Instructor> instructors=new HashSet<>();
}
