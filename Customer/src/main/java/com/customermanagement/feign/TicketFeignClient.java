package com.customermanagement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "taskmanagement")
public interface TicketFeignClient {

    @GetMapping("/api/tasks/user/{userId}")
    List<TaskDto> getTicketByCustomerId(@PathVariable("userId") Integer userId);
}