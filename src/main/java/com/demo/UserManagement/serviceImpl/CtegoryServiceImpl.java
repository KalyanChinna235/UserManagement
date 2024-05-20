package com.demo.UserManagement.serviceImpl;

import com.demo.UserManagement.dto.CategoryDto;
import com.demo.UserManagement.exception.ResourceNotFoundException;
import com.demo.UserManagement.model.Category;
import com.demo.UserManagement.repository.CategoryRepository;
import com.demo.UserManagement.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CtegoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = modelMapper.map(categoryDto, Category.class);
        Category saveCat = categoryRepository.save(cat);
        return modelMapper.map(saveCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updateCat = categoryRepository.save(category);
        return modelMapper.map(updateCat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto getCatById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories =categoryRepository.findAll();
        List<CategoryDto>  categoryDtos= categories.stream().map((cat)-> modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
}
