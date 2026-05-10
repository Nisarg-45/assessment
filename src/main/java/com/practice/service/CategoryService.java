package com.practice.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.practice.exception.ResourceNotFoundException;
import com.practice.model.dto.request.CategoryRequestDto;
import com.practice.model.dto.response.CategoryResponseDto;
import com.practice.model.entity.Category;
import com.practice.repository.CategoryRepository;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;
	public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}
	
	public CategoryResponseDto createCategory(CategoryRequestDto request) {
		
		
	////// entity -> get data from request -> data save into entity -> create responsedto -> get data from saved entity -> return responseDto
//		Category category = new Category();
//		category.setName(request.getName());
//		category.setDescription(request.getDescription());
//		
//		Category saved = categoryRepository.save(category);
// 		
//		CategoryResponseDto response = new CategoryResponseDto();
//		response.setId(saved.getId());
//		response.setName(saved.getName());
//		response.setDescription(saved.getDescription());
//		 
//		return response;

	Category category = modelMapper.map(request,Category.class);
	
	Category saved = categoryRepository.save(category);
	
	return modelMapper.map(saved, CategoryResponseDto.class);
	}
	
	public List<CategoryResponseDto> getAllCategories() {

	    List<Category> categories =
	            categoryRepository.findAll();

	    return categories.stream() 
	            .map(category ->
	                    modelMapper.map(category,CategoryResponseDto.class)
	            )
	          .toList();
	}
	
	
	public CategoryResponseDto getById(Integer id) {
		
		Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category not found"));
		 
		return modelMapper.map(category, CategoryResponseDto.class);
	}
	
	
	public void deleteById(Integer id) {
		Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("not found"));
		
		categoryRepository.deleteById(id);
	}
	
	
	public CategoryResponseDto updateCategory(Integer id, CategoryRequestDto request) {
		Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("not found"));
		

	    category.setName(request.getName());
	    category.setDescription(request.getDescription());

	    
	    Category updated =
	            categoryRepository.save(category);

	    return modelMapper.map(
	            updated,
	            CategoryResponseDto.class
	    );
	    
	}

}
