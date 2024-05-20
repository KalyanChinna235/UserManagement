package com.demo.UserManagement.controller;

import com.demo.UserManagement.config.AppConstants;
import com.demo.UserManagement.dto.ApiResponse;
import com.demo.UserManagement.dto.PostDto;
import com.demo.UserManagement.dto.PostResponse;
import com.demo.UserManagement.service.FileService;
import com.demo.UserManagement.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/add")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Long userId,
                                              @PathVariable Long categoryId) {
        PostDto postDto1 = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(postDto1, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Long userId) {
        List<PostDto> getPostsByUser = postService.getAllPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(getPostsByUser, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Long categoryId) {
        List<PostDto> getPostsByCat = postService.getAllPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(getPostsByCat, HttpStatus.OK);
    }

    @GetMapping("/allPosts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
    ) {
        PostResponse posts = postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        PostDto getPosts = postService.getPostById(postId);
        return new ResponseEntity<>(getPosts, HttpStatus.OK);
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully.", true), HttpStatus.OK);
    }

    @PutMapping("{postId}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable Long postId) {
        PostDto postDtos = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchTitleByKeyword(@PathVariable("keyword") String keyword) {
        List<PostDto> results = postService.searchPosts(keyword);
        return new ResponseEntity<List<PostDto>>(results, HttpStatus.OK);
    }

    // Image upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable Long postId
    ) throws IOException {
        PostDto postDto = postService.getPostById(postId);
        String fileName = fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto updatePost = postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

}
