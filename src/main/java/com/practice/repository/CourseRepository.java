package com.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.practice.model.entity.Course;
import com.practice.model.projection.CourseProjection;

public interface CourseRepository
        extends JpaRepository<Course, Integer> {

    @Query("""
            SELECT c
            FROM Course c
            WHERE c.category.id = :categoryId
            """)
    List<Course> getCoursesByCategory(Integer categoryId);

    @Query("""
            SELECT new com.practice.model.projection.CourseProjection(
                c.id,
                c.title
            )
            FROM Course c
            """)
    List<CourseProjection> getCourseNames();
}