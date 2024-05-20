package com.demo.UserManagement;

import com.demo.UserManagement.controller.CommentController;
import com.demo.UserManagement.dto.ApiResponse;
import com.demo.UserManagement.dto.CommentDto;
import com.demo.UserManagement.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class CommentControllerTest {

    @Mock
    private CommentService commentService;
    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateComments() {
        // Given
        CommentDto commentDto = new CommentDto();
        Long postId = 1L;

        // When
        when(commentService.createComment(any(CommentDto.class), anyLong())).thenReturn(commentDto);
        ResponseEntity<CommentDto> response = commentController.createComments(commentDto, postId);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(commentDto, response.getBody());
        verify(commentService, times(1)).createComment(any(CommentDto.class), anyLong());
    }

    @Test
    public void testDeleteComment() {

        Long commentId = 1L;
        ResponseEntity<ApiResponse> response = commentController.deleteComment(commentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Comment deleted Successfully", response.getBody().getMessage());
        assertEquals(true, response.getBody().isSuccess());
        verify(commentService, times(1)).deleteComment(anyLong());
    }
}

