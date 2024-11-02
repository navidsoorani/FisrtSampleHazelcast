package com.example.hazelcastSample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService service;

    @Autowired
    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        System.out.println(service.toString());
        Ticket createdTicket = service.createTicket(ticket);
        return ResponseEntity.ok(createdTicket);
    }

    @GetMapping
    public Collection<Ticket> getAllTickets() {
        return service.getAllTickets();
    }
}