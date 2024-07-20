package com.customermanagement.service;

import com.customermanagement.Mapper.CustomerMapper;
import com.customermanagement.dto.CustomerDto;
import com.customermanagement.feign.TaskDto;
import com.customermanagement.feign.TicketFeignClient;
import com.customermanagement.model.Customer;
import com.customermanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TicketFeignClient ticketFeignClient;

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toDto)
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomerById(Integer id) {
        return customerRepository.findById(id).map(CustomerMapper::toDto).orElseThrow(() -> new RuntimeException("User not found"));

    }

    public CustomerDto getCustomerWithTicket(Integer userId) {
        Customer Customer = customerRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<TaskDto> tasks = ticketFeignClient.getTicketByCustomerId(userId);
        return new CustomerDto(Customer.getId(), Customer.getName(), Customer.getUsername(), Customer.getPassword(), tasks);
    }

    public CustomerDto createCustomer(CustomerDto userDto) {
        Customer Customer = CustomerMapper.toEntity(userDto);
        Customer savedUser = customerRepository.save(Customer);
        return CustomerMapper.toDto(savedUser);
    }

    public CustomerDto updateCustomer(Integer id, CustomerDto customerDto) {
        Customer updatedUser = new Customer(id, customerDto.name(), customerDto.username(), customerDto.password());
        Customer savedUser = customerRepository.save(updatedUser);
        return CustomerMapper.toDto(savedUser);
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

}
