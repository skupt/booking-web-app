package org.example.booking.mvc.facade;

import org.example.booking.intro.facade.BookingFacade;

public interface BookingWebAppFacade extends BookingFacade {
    public void preloadTickets();
}
