package com.taskmanagement.dto;


import com.taskmanagement.feign.UserDto;

public record TaskDto(String id, String title, String description, String estimation, String status, Integer userId,UserDto userDto) {}