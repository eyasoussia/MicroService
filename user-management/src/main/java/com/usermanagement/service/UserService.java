package com.usermanagement.service;

import com.usermanagement.Mapper.UserMapper;
import com.usermanagement.dto.UserDto;
import com.usermanagement.feign.TaskDto;
import com.usermanagement.feign.TaskFeignClient;
import com.usermanagement.model.UserEntity;
import com.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskFeignClient taskFeignClient;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userEntity -> {

            List<TaskDto> tasks = taskFeignClient.getTasksByUserId(userEntity.getId());
            return new UserDto(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getUsername(), userEntity.getPassword(), userEntity.getRole(), tasks);
        }).collect(Collectors.toList());
    }


    public UserDto getUserById(Integer id) {
        return userRepository.findById(id).map(UserMapper::toDto).orElseThrow(() -> new RuntimeException("User not found"));

    }

    public UserDto getUserWithTasks(Integer userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<TaskDto> tasks = taskFeignClient.getTasksByUserId(userId);
        return new UserDto(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getUsername(), userEntity.getPassword(), userEntity.getRole(), tasks);
    }

    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = UserMapper.toEntity(userDto);
        UserEntity savedUser = userRepository.save(userEntity);
        return UserMapper.toDto(savedUser);
    }

    public UserDto updateUser(Integer id, UserDto userDto) {
        UserEntity updatedUser = new UserEntity(id, userDto.name(), userDto.email(), userDto.username(), userDto.password(), userDto.role()

        );
        UserEntity savedUser = userRepository.save(updatedUser);
        return UserMapper.toDto(savedUser);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }


}
