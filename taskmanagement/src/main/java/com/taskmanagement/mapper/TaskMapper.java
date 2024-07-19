package com.taskmanagement.mapper;

import com.taskmanagement.dto.TaskDto;
import com.taskmanagement.feign.UserDto;
import com.taskmanagement.model.TaskEntity;

public class TaskMapper {
    public static TaskEntity toEntity(TaskDto taskDto) {
        return new TaskEntity(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.estimation(),
                taskDto.status(),
                taskDto.userId(),
                taskDto.userDto()
        );
    }

    public static TaskDto toDto(TaskEntity entity) {
        return new TaskDto(
                entity.id(),
                entity.title(),
                entity.description(),
                entity.estimation(),
                entity.status(),
                entity.userId(),
                entity.userDto()
        );
    }
}
