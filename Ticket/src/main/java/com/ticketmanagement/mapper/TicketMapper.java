package com.ticketmanagement.mapper;

import com.ticketmanagement.dto.TicketDto;
import com.ticketmanagement.model.Ticket;

public class TicketMapper {
    public static Ticket toEntity(TicketDto ticketDto) {
        return new Ticket(
                ticketDto.id(),
                ticketDto.title(),
                ticketDto.description(),
                ticketDto.status(),
                ticketDto.customerId(),
                ticketDto.customerDto()
        );
    }

    public static TicketDto toDto(Ticket entity) {
        return new TicketDto(
                entity.id(),
                entity.title(),
                entity.description(),
                entity.status(),
                entity.customerId(),
                entity.customerDto()
        );
    }
}
