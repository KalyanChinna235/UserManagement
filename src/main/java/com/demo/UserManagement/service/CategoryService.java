package com.demo.UserManagement.service;

import com.demo.UserManagement.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Long id);

    void deleteCategory(Long id);

    CategoryDto getCatById(Long id);

    List<CategoryDto> getAll();
}
