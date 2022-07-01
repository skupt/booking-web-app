package org.example.booking.core.service;

import org.example.booking.core.dao.EventDao;
import org.example.booking.core.dao.TicketDao;
import org.example.booking.core.dao.UserDao;
import org.example.booking.core.model.TicketImpl;
import org.example.booking.intro.model.Event;
import org.example.booking.intro.model.Ticket;
import org.example.booking.intro.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    public static final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private EventDao eventDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AccountService accountService;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        LOGGER.info("BookingTicket transaction starts");

        if (isPlaceFree(eventId, place, category)) {
            TicketImpl ticket = new TicketImpl();
            ticket.setUser(userDao.findById(userId).orElseThrow(() -> new RuntimeException("User for booking does not exist")));
            ticket.setEvent(eventDao.findById(eventId).orElseThrow(() -> new RuntimeException("Event for booking does not exist")));
            ticket.setPlace(place);
            ticket.setCategory(category);
            Ticket ticket2 = ticketDao.save(ticket); // only to show transactions work and return method result
            LOGGER.info("Ticket saved but would rollbacked if money will not be witdrawed later");
            accountService.withdraw(userId, ticket.getEvent().getPrice()); //if money not enough Exception is thrown previous save rollbacks
            LOGGER.info("Money withdrawed");

            LOGGER.info("BookingTicket transaction ends: booked");
            return ticket2;
        }
        LOGGER.info("BookingTicket transaction ends: not booked");
        return null;
    }

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        int offset = pageNum * pageSize;
        return convertToTicketList(ticketDao.findAllByUser(user.getId(), pageSize, offset));
    }

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        int offset = pageNum * pageSize;
        return convertToTicketList(ticketDao.findAllByEvent(event.getId(), pageSize, offset));
    }

    public boolean cancelTicket(long ticketId) {
        ticketDao.deleteById(ticketId);
        return true;
    }

    private boolean isPlaceFree(long eventId, int place, Ticket.Category category) {
        int rows = ticketDao.ticketsOnPlace(eventId, place, category.toString());
        if (rows > 0) return false;
        return true;
    }

    private List<Ticket> convertToTicketList(List<TicketImpl> ticketImplList) {
        return ticketImplList.stream()
                .map(i -> (Ticket) i)
                .collect(Collectors.toList());
    }
}
