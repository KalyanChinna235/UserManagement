package com.demo.UserManagement.exception;

import com.demo.UserManagement.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){

        Map<String,String>  resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)-> {
            String fieldName =  ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            resp.put(fieldName,message);

        });
        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }
}
