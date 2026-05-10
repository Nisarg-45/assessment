package com.practice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.model.dto.request.CategoryRequestDto;
import com.practice.model.dto.response.CategoryResponseDto;
import com.practice.model.dto.response.GenericResponse;
import com.practice.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	 
	@PostMapping
	public GenericResponse<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto request){
			
		return new GenericResponse<>(categoryService.createCategory(request));
	}
	
	@GetMapping
	public GenericResponse<List<CategoryResponseDto>> getAllCategories(){
		
		
		return new GenericResponse<>( categoryService.getAllCategories());
 
	}
	
	@GetMapping("/{id}")
	public GenericResponse<CategoryResponseDto> getById(@PathVariable Integer id){
		return new GenericResponse<>(categoryService.getById(id));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Integer id) {
		 categoryService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public GenericResponse<CategoryResponseDto> updateById(@PathVariable Integer id, @RequestBody CategoryRequestDto request){
		return new GenericResponse<>(categoryService.updateCategory(id, request));
	}
	
}
