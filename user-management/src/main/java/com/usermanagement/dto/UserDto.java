package com.usermanagement.dto;


import com.usermanagement.feign.TaskDto;

import java.util.List;

public record UserDto(
        Integer id,
        String name,
        String email,
        String username,
        String password,
        String role,
        List<TaskDto> tasks
) {
}
