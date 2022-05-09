package com.example.assign1.service;


import com.example.assign1.dto.CategoryDto;

import java.util.List;


public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategorys();

    CategoryDto getCategoryById(long id);

    CategoryDto updateCategory(CategoryDto CategoryDto, long id);

    void deleteCategoryById(long id);
}
