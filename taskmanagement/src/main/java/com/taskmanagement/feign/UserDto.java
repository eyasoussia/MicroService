package com.taskmanagement.feign;


public record UserDto(
        Integer id,
        String name,
        String email,
        String username,
        String password,
        String role
) {}
