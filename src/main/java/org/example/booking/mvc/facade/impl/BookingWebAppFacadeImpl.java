package org.example.booking.mvc.facade.impl;

import org.example.booking.core.facade.BookingFacadeImpl;
import org.example.booking.core.model.TicketImpl;
import org.example.booking.core.service.EventService;
import org.example.booking.core.service.TicketService;
import org.example.booking.core.service.UserService;
import org.example.booking.intro.model.Ticket;
import org.example.booking.mvc.facade.BookingWebAppFacade;
import org.example.booking.mvc.utils.XmlHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class BookingWebAppFacadeImpl extends BookingFacadeImpl implements BookingWebAppFacade {

    public static final Logger LOGGER = LoggerFactory.getLogger(BookingWebAppFacadeImpl.class);

    public BookingWebAppFacadeImpl(EventService eventService, UserService userService, TicketService ticketService) {
        super(eventService, userService, ticketService);
    }

    private String bookingMvcXmlInputFilename;
    private final XmlHelper xmlHelper = new XmlHelper();

    public void setBookingMvcXmlInputFilename(String bookingMvcXmlInputFilename) {
        this.bookingMvcXmlInputFilename = bookingMvcXmlInputFilename;
    }

    @Override
    public void preloadTickets() {
        String realFilePath = this.getClass().getClassLoader().getResource(bookingMvcXmlInputFilename).getFile();
        File file = new File(realFilePath);
        String xmlContent;
        try {
            xmlContent = Files.readString(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new RuntimeException("IOException while read xml input file for tickets", e);
        }
        List<TicketImpl> ticketList = xmlHelper.fromXml(xmlContent).getTickets();
        for (Ticket ticket : ticketList) {
            bookTicket(ticket.getUserId(), ticket.getEventId(), ticket.getPlace(), ticket.getCategory());
        }
    }
}