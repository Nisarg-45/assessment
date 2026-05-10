package com.practice.model.projection;

public class CategoryProjection {

    private Integer id;
    private String name;

    public CategoryProjection(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}