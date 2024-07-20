package com.ticketmanagement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "customer-Management")
public interface CustomerFeignClient {

    @GetMapping("/api/customers/{id}")
    CustomerDto getCustomerById(@PathVariable("id") Integer id);
}
