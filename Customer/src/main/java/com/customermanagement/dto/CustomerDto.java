package com.customermanagement.dto;


import com.customermanagement.feign.TaskDto;

import java.util.List;

public record CustomerDto(
        Integer id,
        String name,
        String username,
        String password,
        List<TaskDto> tasks
) {
}
