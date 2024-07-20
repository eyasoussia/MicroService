package com.customermanagement.feign;



public record TaskDto(String id, String title, String description, String estimation, String status, Integer userId) {}