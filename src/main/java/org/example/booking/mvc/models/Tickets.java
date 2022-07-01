package org.example.booking.mvc.models;

import org.example.booking.core.model.TicketImpl;

import java.util.ArrayList;

public class Tickets {
    private ArrayList<TicketImpl> tickets;

    public ArrayList<TicketImpl> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<TicketImpl> tickets) {
        this.tickets = tickets;
    }
}
