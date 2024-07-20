package com.ticketmanagement.controller;

import com.ticketmanagement.dto.TicketDto;
import com.ticketmanagement.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<TicketDto> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public TicketDto getTicketById(@PathVariable String id) {
        return ticketService.getTicketById(id);
    }

    @PostMapping
    public TicketDto createTicket(@RequestBody TicketDto ticketDto) {
        return ticketService.createTicket(ticketDto);
    }

    @PutMapping("/{id}")
    public TicketDto updateTicket(@PathVariable String id, @RequestBody TicketDto ticketDto) {
        return ticketService.updateTicket(id, ticketDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable String id) {
        ticketService.deleteTicket(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<TicketDto> getTicketsByCustomerId(@PathVariable("userId") Integer customerId) {
        return ticketService.getTicketByCustomerId(customerId);
    }

}
