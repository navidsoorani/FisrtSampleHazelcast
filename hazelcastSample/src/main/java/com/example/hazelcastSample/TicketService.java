package com.example.hazelcastSample;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Service
public class TicketService {

    private final HazelcastInstance hzInstance;
    private final String ticketQueueName;

    @Autowired
    public TicketService(
            HazelcastInstance hzInstance,
            @Value("${ticket.queue.name}") String ticketQueueName
    ) {
        this.hzInstance = hzInstance;
        this.ticketQueueName = ticketQueueName;
    }

    public Ticket createTicket(Ticket ticket) {
        IQueue<Ticket> ticketQueue = hzInstance.getQueue( ticketQueueName );
        ticket.setId(UUID.randomUUID().toString());
        ticketQueue.offer(ticket);
        return ticket;
    }

    public Collection<Ticket> getAllTickets() {
        Collection<Ticket> tickets = new ArrayList<>();
        IQueue<Ticket> ticketQueue = hzInstance.getQueue( ticketQueueName );
        while(!ticketQueue.isEmpty()) {
            tickets.add(ticketQueue.poll());
        }
        return tickets;
    }
}