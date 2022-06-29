package org.example.booking.core.dao;

import org.example.booking.core.model.TicketImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDao extends JpaRepository<TicketImpl, Long> {
//    private Storage storage;
//
//    public void setStorage(Storage storage) {
//        this.storage = storage;
//    }
//
//    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
//        return storage.bookTicket(userId, eventId, place, category);
//    }
//
//    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
//        return storage.getBookedTickets(user, pageSize, pageNum);
//    }
//
//    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
//        return storage.getBookedTickets(event, pageSize, pageNum);
//    }
//
//    public boolean cancelTicket(long ticketId) {
//        return storage.cancelTicket(ticketId);
//    }
}
