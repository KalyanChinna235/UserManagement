package com.demo.UserManagement.serviceImpl;

import com.demo.UserManagement.dto.CommentDto;
import com.demo.UserManagement.exception.ResourceNotFoundException;
import com.demo.UserManagement.model.Comment;
import com.demo.UserManagement.model.Post;
import com.demo.UserManagement.repository.CommentRepository;
import com.demo.UserManagement.repository.PostRepository;
import com.demo.UserManagement.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment comment1 = commentRepository.save(comment);
        return modelMapper.map(comment1, CommentDto.class );
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", id));
        commentRepository.delete(comment);
    }
}
