package com.practice.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.model.dto.request.CourseRequestDto;
import com.practice.model.dto.response.CourseResponseDto;
import com.practice.model.dto.response.GenericResponse;
import com.practice.model.projection.CourseProjection;
import com.practice.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(
            CourseService courseService) {

        this.courseService = courseService;
    }

    // CREATE COURSE
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public GenericResponse<CourseResponseDto> 
    createCourse(
            @RequestBody CourseRequestDto request) {

        return new GenericResponse<>(
                courseService.createCourse(request)
        );
    }
 
    // GET ALL COURSES
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public GenericResponse<List<CourseResponseDto>>
    getAllCourses() {

        return new GenericResponse<>(
                courseService.getAllCourses()
        );
    }

    // GET COURSE BY ID
    @GetMapping("/{id}")
    public GenericResponse<CourseResponseDto>
    getById(
            @PathVariable Integer id) {

        return new GenericResponse<>(
                courseService.getById(id)
        );
    }

    // UPDATE COURSE
    @PutMapping("/{id}")
    public GenericResponse<CourseResponseDto>
    updateCourse(
            @PathVariable Integer id,
            @RequestBody CourseRequestDto request) {

        return new GenericResponse<>(
                courseService.updateCourse(id, request)
        );
    }

    // DELETE COURSE
    @DeleteMapping("/{id}")
    public GenericResponse<String>
    deleteById(
            @PathVariable Integer id) {

        courseService.deleteById(id);

        return new GenericResponse<>(
                "Course deleted successfully"
        );
    }

    // GET COURSES BY CATEGORY
    @GetMapping("/category/{categoryId}")
    public GenericResponse<
            List<CourseResponseDto>> 
    getCoursesByCategory(
            @PathVariable Integer categoryId) {

        return new GenericResponse<>(
                courseService.getCoursesByCategory(
                        categoryId
                )
        );
    }

    // PROJECTION API
    @GetMapping("/names")
    public GenericResponse<
            List<CourseProjection>> 
    getCourseNames() {
 
        return new GenericResponse<>(
                courseService.getCourseNames()
        );
    } 
    
    @PutMapping("/{courseId}/category/{categoryId}")
    
    public GenericResponse<CourseResponseDto> assignCourseToCategory(@PathVariable Integer courseId, @PathVariable Integer categoryId){
    	
    		return new GenericResponse<>(courseService.assignCourseToCategory(categoryId, courseId));
    }
}