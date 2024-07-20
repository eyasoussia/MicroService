package com.customermanagement.Mapper;

import com.customermanagement.dto.CustomerDto;

import com.customermanagement.model.Customer;

import java.util.List;


public class CustomerMapper {

    public static Customer toEntity(CustomerDto userDto) {
        return new Customer(
                null,
                userDto.name(),
                userDto.username(),
                userDto.password()
        );
    }

    public static CustomerDto toDto(Customer Customer) {
        return new CustomerDto(
                Customer.getId(),
                Customer.getName(),
                Customer.getUsername(),
                Customer.getPassword(),
                List.of()

        );
    }
}
