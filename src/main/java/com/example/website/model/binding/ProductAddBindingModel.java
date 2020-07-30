package com.example.website.model.binding;

import com.example.website.model.entity.Category;
import com.example.website.model.entity.Product;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class ProductAddBindingModel {
    private String name;
    private String gender;
    private String description;
    private BigDecimal price;
    private CategoryAddBindingModel categoryAddBindingModel;
    private boolean isDeleted;

    public ProductAddBindingModel() {
    }

    @Length(min = 2,message = "Product name length must be more than two characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Length(min = 4,message = "Enter valid gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @Length(min = 3,message = "Description length must be more than three characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DecimalMin(value = "0",message = "Enter valid price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryAddBindingModel getCategoryAddBindingModel() {
        return categoryAddBindingModel;
    }

    public void setCategoryAddBindingModel(CategoryAddBindingModel categoryAddBindingModel) {
        this.categoryAddBindingModel = categoryAddBindingModel;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
