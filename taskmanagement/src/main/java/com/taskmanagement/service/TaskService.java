package com.taskmanagement.service;


import com.taskmanagement.dto.TaskDto;
import com.taskmanagement.feign.UserDto;
import com.taskmanagement.feign.UserFeignClient;
import com.taskmanagement.mapper.TaskMapper;
import com.taskmanagement.model.TaskEntity;
import com.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserFeignClient userFeignClient;
    /*public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());
    }*/
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskEntity -> {
                    UserDto userDto = userFeignClient.getUserById(taskEntity.userId());
                    return new TaskDto(
                            taskEntity.id(),
                            taskEntity.title(),
                            taskEntity.description(),
                            taskEntity.estimation(),
                            taskEntity.status(),
                            taskEntity.userId(),
                            userDto
                    );
                })
                .collect(Collectors.toList());
    }

    public TaskDto getTaskById(String id) {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        UserDto userDto = userFeignClient.getUserById(taskEntity.userId());

        return new TaskDto(
                taskEntity.id(),
                taskEntity.title(),
                taskEntity.description(),
                taskEntity.estimation(),
                taskEntity.status(),
                taskEntity.userId(),
                userDto
        );
    }

    public TaskDto createTask(TaskDto taskDto) {
        TaskEntity entity = TaskMapper.toEntity(taskDto);
        TaskEntity savedEntity = taskRepository.save(entity);
        return TaskMapper.toDto(savedEntity);
    }

    public TaskDto updateTask(String id, TaskDto taskDto) {
        TaskEntity entity = new TaskEntity(
                id,
                taskDto.title(),
                taskDto.description(),
                taskDto.estimation(),
                taskDto.status(),
                taskDto.userId(),
                taskDto.userDto()
        );
        TaskEntity updatedEntity = taskRepository.save(entity);
        return TaskMapper.toDto(updatedEntity);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
    public List<TaskDto> getTasksByUserId(Integer userId) {
        return taskRepository.findByUserId(userId).stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());
    }
}