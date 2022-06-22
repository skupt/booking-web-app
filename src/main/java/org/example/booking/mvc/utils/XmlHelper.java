package org.example.booking.mvc.utils;

import com.thoughtworks.xstream.XStream;
import org.example.booking.core.model.TicketImpl;
import org.example.booking.mvc.models.Tickets;

public class XmlHelper {
    private final XStream xstream = new XStream();

    {
        xstream.allowTypesByWildcard(new String[]{"org.example.booking.**"});
        xstream.alias("ticket", TicketImpl.class);
        xstream.alias("tickets", Tickets.class);
        xstream.addImplicitCollection(Tickets.class, "tickets", TicketImpl.class);
        xstream.omitField(TicketImpl.class, "id");
        xstream.useAttributeFor(TicketImpl.class, "eventId");
        xstream.useAttributeFor(TicketImpl.class, "userId");
        xstream.useAttributeFor(TicketImpl.class, "category");
        xstream.useAttributeFor(TicketImpl.class, "place");

    }

    public String toXml(Tickets tickets) {
        return xstream.toXML(tickets);
    }

    public Tickets fromXml(String ticketsString) {
        return (Tickets) xstream.fromXML(ticketsString);
    }


}
