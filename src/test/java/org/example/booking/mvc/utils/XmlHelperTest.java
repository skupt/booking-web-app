package org.example.booking.mvc.utils;

import org.example.booking.core.model.EventImpl;
import org.example.booking.core.model.TicketImpl;
import org.example.booking.core.model.UserImpl;
import org.example.booking.intro.model.Ticket;
import org.example.booking.intro.model.User;
import org.example.booking.mvc.models.Tickets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class XmlHelperTest {
    private static XmlHelper xmlHelper = new XmlHelper();
    private static final Tickets tickets;

    static {
        tickets = new Tickets();
        ArrayList<TicketImpl> ticketList = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            UserImpl user = new UserImpl();
            user.setId(i);
            EventImpl event = new EventImpl();
            event.setId(i);
            ticketList.add(new TicketImpl(i, event, user, Ticket.Category.BAR, i));
        }
        tickets.setTickets(ticketList);
    }

    private static final String expectedXml = "<tickets>\n" +
            "  <ticket eventId=\"1\" userId=\"1\" category=\"BAR\" place=\"1\"/>\n" +
            "  <ticket eventId=\"2\" userId=\"2\" category=\"BAR\" place=\"2\"/>\n" +
            "</tickets>";

    @Test
    public void toXmlTest() {
        String actual = xmlHelper.toXml(tickets);

        Assertions.assertEquals(actual, expectedXml);
    }

    @Test
    public void fromXmlTest() {
        Object actualTickets = xmlHelper.fromXml(expectedXml);
        Tickets tickets = (Tickets) actualTickets;
        Ticket ticket2 = tickets.getTickets().get(1);

        Assertions.assertEquals(2, tickets.getTickets().size());
        Assertions.assertEquals(0, ticket2.getId());
        Assertions.assertEquals(2, ticket2.getEventId());

    }


}
