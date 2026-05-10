package com.practice.model.dto.request;

public class CourseRequestDto {

    private String title;

    private String description;

    private Double price;

    private Integer categoryId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public CourseRequestDto() {}
	public CourseRequestDto(String title, String description, Double price, Integer categoryId) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.categoryId = categoryId;
	}

    // getters setters
}