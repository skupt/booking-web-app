package org.example.booking.core.service;

import org.example.booking.core.dao.TicketDao;
import org.example.booking.core.model.EventImpl;
import org.example.booking.core.model.TicketImpl;
import org.example.booking.core.model.UserImpl;
import org.example.booking.intro.model.Event;
import org.example.booking.intro.model.Ticket;
import org.example.booking.intro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    private TicketDao ticketDao;

    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
//        return ticketDao.bookTicket(userId, eventId, place, category);
        if (isPlaceFree(eventId, place, category)) {
            TicketImpl ticket = new TicketImpl();
            ticket.setUserId(userId);
            ticket.setEventId(eventId);
            ticket.setPlace(place);
            ticket.setCategory(category);
            return ticketDao.save(ticket);
        }
        return null;
    }

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
//        return ticketDao.getBookedTickets(user, pageSize, pageNum);
        TicketImpl ticketProbe = new TicketImpl();
        ticketProbe.setUser((UserImpl) user);
        return convertToTicketList(ticketDao.findAll(Example.of(ticketProbe), PageRequest.of(pageNum, pageSize)).toList());
    }

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
//        return ticketDao.getBookedTickets(event, pageSize, pageNum);
        TicketImpl ticketProbe = new TicketImpl();
        ticketProbe.setEvent((EventImpl) event);
        return convertToTicketList(ticketDao.findAll(Example.of(ticketProbe), PageRequest.of(pageNum, pageSize)).toList());
    }

    public boolean cancelTicket(long ticketId) {
//        return ticketDao.cancelTicket(ticketId);
        ticketDao.deleteById(ticketId);
        return true;
    }

    private boolean isPlaceFree(long eventId, int place, Ticket.Category category) {
        TicketImpl ticketProbe = new TicketImpl();
        ticketProbe.setEventId(eventId);
        ticketProbe.setCategory(category);
        ticketProbe.setPlace(place);
        return !ticketDao.findAll(Example.of(ticketProbe)).isEmpty();
    }

    private List<Ticket> convertToTicketList(List<TicketImpl> ticketImplList) {
        return ticketImplList.stream()
                .map(i -> (Ticket) i)
                .collect(Collectors.toList());
    }
}
