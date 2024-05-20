package com.demo.UserManagement.controller;

import com.demo.UserManagement.dto.ApiResponse;
import com.demo.UserManagement.dto.CommentDto;
import com.demo.UserManagement.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "Create a new comment for a post", response = CommentDto.class)
    @PostMapping("/post/{postId}/add")
    public ResponseEntity<CommentDto> createComments(@RequestBody CommentDto commentDto, @PathVariable Long postId) {

        CommentDto commentDto1 = commentService.createComment(commentDto, postId);

        return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a comment by ID", notes = "Deletes a comment based on the provided ID")
    @ApiResponses(
            value = {
                    @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully deleted comment"),
                    @io.swagger.annotations.ApiResponse(code = 404, message = "Comment not found")
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted Successfully", true), HttpStatus.OK);
    }
}
