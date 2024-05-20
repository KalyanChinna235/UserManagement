package com.demo.UserManagement;

import com.demo.UserManagement.controller.PostController;
import com.demo.UserManagement.dto.ApiResponse;
import com.demo.UserManagement.dto.PostDto;
import com.demo.UserManagement.dto.PostResponse;
import com.demo.UserManagement.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class PostControllerTest {
    @Mock
    private PostService postService;
    @InjectMocks
    private PostController postController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePost() {
        //Given
        PostDto postDto = new PostDto();
        //When
        when(postService.createPost(any(PostDto.class), anyLong(), anyLong())).thenReturn(postDto);
        ResponseEntity<PostDto> response = postController.createPost(postDto, 1L, 1L);
        //Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(postDto, response.getBody());
        verify(postService, times(1)).createPost(any(PostDto.class), anyLong(), anyLong());
    }

    @Test
    public void testGetPostByCategory() {
        List<PostDto> postDtoList = Collections.singletonList(new PostDto());

        when(postService.getAllPostsByCategory(anyLong())).thenReturn(postDtoList);
        ResponseEntity<List<PostDto>> response = postController.getPostsByCategory(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDtoList, response.getBody());
        verify(postService, times(1)).getAllPostsByCategory(anyLong());
    }

    @Test
    public void testGetPostByUser() {
        List<PostDto> postDtosList = Collections.singletonList(new PostDto());

        when(postService.getAllPostsByUser(anyLong())).thenReturn(postDtosList);
        ResponseEntity<List<PostDto>> response = postController.getPostsByUser(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDtosList, response.getBody());
        verify(postService, times(1)).getAllPostsByUser(anyLong());
    }

    @Test
    public void testGetPostById() {
        PostDto postDto = new PostDto();

        when(postService.getPostById(anyLong())).thenReturn(postDto);
        ResponseEntity<PostDto> response = postController.getPostById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDto, response.getBody());
        verify(postService, times(1)).getPostById(anyLong());
    }

    @Test
    public void testDeletePostById() {
        // When
        ResponseEntity<ApiResponse> response = postController.deletePostById(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Post deleted successfully.", response.getBody().getMessage());
        assertEquals(true, response.getBody().isSuccess());
        verify(postService, times(1)).deletePost(anyLong());
    }
    @Test
    public void testUpdatePostById() {
        // Given
        PostDto postDto = new PostDto();

        // When
        when(postService.updatePost(any(PostDto.class), anyLong())).thenReturn(postDto);
        ResponseEntity<PostDto> response = postController.updatePostById(postDto, 1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDto, response.getBody());
        verify(postService, times(1)).updatePost(any(PostDto.class), anyLong());
    }

    @Test
    public void testSearchTitleByKeyword() {
        // Given
        List<PostDto> postDtoList = Collections.singletonList(new PostDto());

        // When
        when(postService.searchPosts(anyString())).thenReturn(postDtoList);
        ResponseEntity<List<PostDto>> response = postController.searchTitleByKeyword("test");

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDtoList, response.getBody());
        verify(postService, times(1)).searchPosts(anyString());
    }
}
