package com.demo.UserManagement.service;

import com.demo.UserManagement.dto.UserDto;

import java.util.List;

public interface UserService {


    public UserDto createUser(UserDto userDto);

    public UserDto getUser(Long userId);

    public List<UserDto> getAll();

    public UserDto updateUser(UserDto userDto, Long id);

    public void deleteUser(Long id);
}
