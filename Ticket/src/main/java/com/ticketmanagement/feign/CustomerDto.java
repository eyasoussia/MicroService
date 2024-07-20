package com.ticketmanagement.feign;

public record CustomerDto(
        Integer id,
        String name,
        String username,
        String password
) {
}
