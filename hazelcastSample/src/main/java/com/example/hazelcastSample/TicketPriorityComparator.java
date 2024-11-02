package com.example.hazelcastSample;

import java.util.Comparator;

public class TicketPriorityComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket first, Ticket second) {
        return first.getPriority().compareTo(second.getPriority());
    }
}