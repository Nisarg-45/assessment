package com.practice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practice.model.entity.Category;
import com.practice.model.projection.CategoryProjection;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	//Optional<Category> findByName(String name);

	@Query("SELECT c FROM Category c WHERE c.name = :name")
	Optional<Category> getCategoryByName(String name);

	@Query("""
			SELECT new com.practice.model.projection.CategoryProjection(
			 c.id,
			 c.name
			 )
			FROM Category c

			""")
	List<CategoryProjection> getAllCategoryNames();
}
