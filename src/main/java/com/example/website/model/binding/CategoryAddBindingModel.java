package com.example.website.model.binding;

import org.hibernate.validator.constraints.Length;

public class CategoryAddBindingModel {
    private Long id;

    private String name;

    public CategoryAddBindingModel() {
    }

    @Length(min = 2,message = "Category name length must be more than two characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
