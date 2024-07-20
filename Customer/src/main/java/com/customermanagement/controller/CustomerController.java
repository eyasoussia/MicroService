package com.customermanagement.controller;

import com.customermanagement.dto.CustomerDto;
import com.customermanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/Customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerDto> getAllUCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/{id}/ticket")
    public CustomerDto getCustomerWithTasks(@PathVariable Integer id) {
        return customerService.getCustomerWithTicket(id);
    }

    @PostMapping
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

    @PutMapping("/{id}")
    public CustomerDto updateCustomer(@PathVariable Integer id, @RequestBody CustomerDto userDto) {
        return customerService.updateCustomer(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
    }

}

