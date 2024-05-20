package com.demo.UserManagement.service;

import com.demo.UserManagement.dto.PostDto;
import com.demo.UserManagement.dto.PostResponse;
import com.demo.UserManagement.model.Post;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Long userId, Long categoryId);

    PostDto updatePost(PostDto postDto, Long postId);

    void deletePost(Long postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    PostDto getPostById(Long postId);

    List<PostDto> getAllPostsByCategory(Long categoryId);

    List<PostDto> getAllPostsByUser(Long userId);

    List<PostDto> searchPosts(String keyword);
}
