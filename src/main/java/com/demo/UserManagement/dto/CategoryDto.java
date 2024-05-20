package com.demo.UserManagement.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

    private Long categoryId;
    @NotEmpty
    @Size(min = 3, message = "Name must be minimum 3 charactors !!")
    private String categoryTitle;
    @NotEmpty
    @Size(min = 10, message = "Name must be minimum 3 charactors !!")
    private String categoryDescription;
}
