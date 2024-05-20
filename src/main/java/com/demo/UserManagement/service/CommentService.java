package com.demo.UserManagement.service;

import com.demo.UserManagement.dto.CommentDto;

public interface CommentService {

        CommentDto createComment(CommentDto commentDto,Long postId);
        void deleteComment(Long id);
}
