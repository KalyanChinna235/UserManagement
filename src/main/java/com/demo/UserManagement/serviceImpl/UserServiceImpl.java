package com.demo.UserManagement.serviceImpl;

import com.demo.UserManagement.dto.UserDto;
import com.demo.UserManagement.exception.ResourceNotFoundException;
import com.demo.UserManagement.model.User;
import com.demo.UserManagement.repository.UserRepository;
import com.demo.UserManagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Creating user: {}", userDto);
        User user = userDtoToUser(userDto);
        User savedUser = userRepository.save(user);
        UserDto savedUserDto = userToUserDto(savedUser);
        log.info("User created successfully: {}", savedUserDto);
        return savedUserDto;
    }

    @Override
    public UserDto getUser(Long userId) {
        log.info("Fetching user with ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        UserDto userDto = userToUserDto(user);
        log.info("User fetched successfully: {}", userDto);
        return userDto;
    }

    @Override
    public List<UserDto> getAll() {
        log.info("Fetching all users");
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(this::userToUserDto)
                .collect(Collectors.toList());
        log.info("Fetched {} users", userDtos.size());
        return userDtos;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        log.info("Updating user with ID {}: {}", id, userDto);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(user);
        UserDto updatedUserDto = userToUserDto(updatedUser);
        log.info("User updated successfully: {}", updatedUserDto);
        return updatedUserDto;
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        userRepository.delete(user);
        log.info("User deleted successfully");
    }

    private User userDtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDto userToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
