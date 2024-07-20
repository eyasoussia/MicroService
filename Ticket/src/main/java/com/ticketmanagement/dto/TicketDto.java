package com.ticketmanagement.dto;


import com.ticketmanagement.feign.CustomerDto;

public record TicketDto(String id, String title, String description, String status, Integer customerId, CustomerDto customerDto) {}
