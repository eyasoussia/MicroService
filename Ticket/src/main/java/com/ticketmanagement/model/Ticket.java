package com.ticketmanagement.model;

import com.ticketmanagement.feign.CustomerDto;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "ticket")
public record Ticket(
        @Id String id,
        String title,
        String description,
        String status,
        Integer customerId,
        CustomerDto customerDto
) {}