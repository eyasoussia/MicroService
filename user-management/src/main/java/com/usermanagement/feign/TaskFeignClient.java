package com.usermanagement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "taskmanagement")
public interface TaskFeignClient {

    @GetMapping("/api/tasks/user/{userId}")
    List<TaskDto> getTasksByUserId(@PathVariable("userId") Integer userId);
}