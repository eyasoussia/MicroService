package com.taskmanagement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-Management")
public interface UserFeignClient {

    @GetMapping("/api/users/{id}")
    UserDto getUserById(@PathVariable("id") Integer id);
}
