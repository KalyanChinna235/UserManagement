package com.demo.UserManagement;

import com.demo.UserManagement.controller.CategoryController;
import com.demo.UserManagement.dto.ApiResponse;
import com.demo.UserManagement.dto.CategoryDto;
import com.demo.UserManagement.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCategory() {
        CategoryDto categoryDto = new CategoryDto();

        when(categoryService.createCategory(any(CategoryDto.class))).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = categoryController.createCategory(categoryDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(categoryDto, response.getBody());
        verify(categoryService, times(1)).createCategory(any(CategoryDto.class));
    }

    @Test
    public void testUpdateCategory() {

        Long categoryId = 1L;
        CategoryDto categoryDto = new CategoryDto();

        when(categoryService.updateCategory(any(CategoryDto.class), eq(categoryId))).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = categoryController.updateCategory(categoryDto, categoryId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(categoryDto, response.getBody());

        verify(categoryService, times(1)).updateCategory(any(CategoryDto.class), eq(categoryId));

    }

    @Test
    public void testDeleteCategory() {

        Long categoryId = 1L;
        doNothing().when(categoryService).deleteCategory(categoryId);
        ResponseEntity<ApiResponse> response = categoryController.deleteCategory(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User deleted successfully.", response.getBody().getMessage());
        assertEquals(true, response.getBody().isSuccess());
        verify(categoryService, times(1)).deleteCategory(categoryId);
    }

    @Test
    public void testGetCategory() {
        Long categoryId = 1L;
        CategoryDto categoryDto = new CategoryDto();
        when(categoryService.getCatById(categoryId)).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = categoryController.getCategory(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryDto, response.getBody());
        verify(categoryService, times(1)).getCatById(categoryId);

    }

    @Test
    public void testGetAllCategory() {
        List<CategoryDto> categoryDtos = Arrays.asList(new CategoryDto(), new CategoryDto());
        when(categoryService.getAll()).thenReturn(categoryDtos);

        ResponseEntity<List<CategoryDto>> response = categoryController.getAllCategory();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryDtos, response.getBody());
        verify(categoryService, times(1)).getAll();
    }
}
