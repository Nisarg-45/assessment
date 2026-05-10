package com.practice.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.practice.exception.ResourceNotFoundException;
import com.practice.model.dto.request.CourseRequestDto;
import com.practice.model.dto.response.CourseResponseDto;
import com.practice.model.entity.Category;
import com.practice.model.entity.Course;
import com.practice.model.projection.CourseProjection;
import com.practice.repository.CategoryRepository;
import com.practice.repository.CourseRepository;

//@Service
//public class CourseService {
//
//    private final CourseRepository courseRepository;
//    private final CategoryRepository categoryRepository;
//    private final ModelMapper modelMapper;
//
//    public CourseService(
//            CourseRepository courseRepository,
//            CategoryRepository categoryRepository,
//            ModelMapper modelMapper) {
//
//        this.courseRepository = courseRepository;
//        this.categoryRepository = categoryRepository;
//        this.modelMapper = modelMapper;
//    }
//
//    // CREATE COURSE
//    public CourseResponseDto createCourse(CourseRequestDto request) {
//
//        Category category = categoryRepository.findById(
//                        request.getCategoryId()
//                ).orElseThrow(() -> new ResourceNotFoundException("Category not found")
//        );
//
//        Course course = new Course();
//        
//        // Manual Setters
//        course.setId(null); // Explicitly null for creation safety
//        course.setTitle(request.getTitle());
//        course.setDescription(request.getDescription());
//        course.setPrice(request.getPrice());
//        course.setCategory(category);
//
//        Course saved = courseRepository.save(course);
//
//        return mapToResponse(saved);
//    }
//
//    // GET ALL COURSES
//    public List<CourseResponseDto> getAllCourses() {
//
//        List<Course> courses = courseRepository.findAll();
//        List<CourseResponseDto> response = new ArrayList<>();
//
//        for (Course course : courses) {
//            response.add(mapToResponse(course));
//        }
//
//        return response;
//    }
//
//    // GET COURSE BY ID
//    public CourseResponseDto getById(Integer id) {
//
//        Course course = courseRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
//
//        return mapToResponse(course);
//    }
//
//    // DELETE COURSE
//    public void deleteById(Integer id) {
//
//        Course course = courseRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
//
//        courseRepository.delete(course);
//    }
//
//    // UPDATE COURSE
//    public CourseResponseDto updateCourse(Integer id, CourseRequestDto request) {
//
//        Course course = courseRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
//
//        Category category = categoryRepository.findById(
//                        request.getCategoryId()
//                ).orElseThrow(() -> new ResourceNotFoundException("Category not found")
//        );
//
//        // Manual Setters for Update
//        course.setTitle(request.getTitle());
//        course.setDescription(request.getDescription());
//        course.setPrice(request.getPrice());
//        course.setCategory(category);
//
//        Course updated = courseRepository.save(course);
//
//        return mapToResponse(updated);
//    }
//
//    // GET COURSES BY CATEGORY
//    public List<CourseResponseDto> getCoursesByCategory(Integer categoryId) {
//
//        List<Course> courses = courseRepository.getCoursesByCategory(categoryId);
//        List<CourseResponseDto> response = new ArrayList<>();
//
//        for (Course course : courses) {
//            response.add(mapToResponse(course));
//        }
//
//        return response;
//    }
//
//    // PROJECTION
//    public List<CourseProjection> getCourseNames() {
//        return courseRepository.getCourseNames();
//    }
//
//    // ENTITY -> DTO (MANUAL MAPPING)
//    private CourseResponseDto mapToResponse(Course course) {
//
//        CourseResponseDto response = new CourseResponseDto();
//
//        response.setId(course.getId());
//        response.setTitle(course.getTitle());
//        response.setDescription(course.getDescription());
//        response.setPrice(course.getPrice());
//
//        if (course.getCategory() != null) {
//            response.setCategoryName(course.getCategory().getName());
//        }
//
//        return response;
//    }
//}


@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CourseService(CourseRepository courseRepository, 
                         CategoryRepository categoryRepository, 
                         ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    // CREATE
    public CourseResponseDto createCourse(CourseRequestDto request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Course course = modelMapper.map(request, Course.class);
        course.setId(null); 
        course.setCategory(category);

        Course saved = courseRepository.save(course);
        return modelMapper.map(saved, CourseResponseDto.class);
    }

    // GET ALL
    public List<CourseResponseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> modelMapper.map(course, CourseResponseDto.class))
                .toList();
    }

    // GET BY ID
    public CourseResponseDto getById(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        
        return modelMapper.map(course, CourseResponseDto.class);
    }

    // DELETE
    public void deleteById(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        
        courseRepository.delete(course);
    }

    // UPDATE
    public CourseResponseDto updateCourse(Integer id, CourseRequestDto request) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        // Use modelMapper to update fields from request to existing entity
        modelMapper.map(request, existingCourse);
        
        existingCourse.setId(id);
        existingCourse.setCategory(category);

        Course updated = courseRepository.save(existingCourse);
        return modelMapper.map(updated, CourseResponseDto.class);
    }

    // GET BY CATEGORY
    public List<CourseResponseDto> getCoursesByCategory(Integer categoryId) {
        return courseRepository.getCoursesByCategory(categoryId).stream()
                .map(course -> modelMapper.map(course, CourseResponseDto.class))
                .toList();
    }

    // PROJECTION
    public List<CourseProjection> getCourseNames() {
        return courseRepository.getCourseNames();
    }
}
