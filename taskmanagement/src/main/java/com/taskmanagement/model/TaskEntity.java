package com.taskmanagement.model;

import com.taskmanagement.feign.UserDto;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "tasks")
public record TaskEntity(
        @Id String id,
        String title,
        String description,
        String estimation,
        String status,
        Integer userId,
        UserDto userDto
) {}