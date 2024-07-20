package com.ticketmanagement.service;


import com.ticketmanagement.dto.TicketDto;
import com.ticketmanagement.feign.CustomerDto;
import com.ticketmanagement.feign.CustomerFeignClient;
import com.ticketmanagement.mapper.TicketMapper;
import com.ticketmanagement.model.Ticket;
import com.ticketmanagement.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CustomerFeignClient customerFeignClient;
    public List<TicketDto> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(TicketMapper::toDto)
                .collect(Collectors.toList());
    }

    public TicketDto getTicketById(String id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        CustomerDto customerDto = customerFeignClient.getCustomerById(ticket.customerId());

        return new TicketDto(
                ticket.id(),
                ticket.title(),
                ticket.description(),
                ticket.status(),
                ticket.customerId(),
                customerDto
        );
    }

    public TicketDto createTicket(TicketDto ticketDto) {
        Ticket entity = TicketMapper.toEntity(ticketDto);
        Ticket savedEntity = ticketRepository.save(entity);
        return TicketMapper.toDto(savedEntity);
    }

    public TicketDto updateTicket(String id, TicketDto ticketDto) {
        Ticket entity = new Ticket(
                id,
                ticketDto.title(),
                ticketDto.description(),
                ticketDto.status(),
                ticketDto.customerId(),
                ticketDto.customerDto()
        );
        Ticket updatedEntity = ticketRepository.save(entity);
        return TicketMapper.toDto(updatedEntity);
    }

    public void deleteTicket(String id) {
        ticketRepository.deleteById(id);
    }
    public List<TicketDto> getTicketByCustomerId(Integer customerId) {
        return ticketRepository.findByCustomerId(customerId).stream()
                .map(TicketMapper::toDto)
                .collect(Collectors.toList());
    }
}