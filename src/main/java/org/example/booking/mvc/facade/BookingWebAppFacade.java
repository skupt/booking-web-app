package org.example.booking.mvc.facade;

import org.example.booking.intro.facade.BookingFacade;

import java.math.BigDecimal;

public interface BookingWebAppFacade extends BookingFacade {
    void preloadTickets();

    BigDecimal refillAccount(long userId, BigDecimal moneyAmount);

    BigDecimal checkAccount(long userId);

    BigDecimal withdrawAccount(long userId, BigDecimal moneyAmount);

}
