package org.example.booking.mvc.facade;

import java.math.BigDecimal;

public interface BookingWebAppFacade {
    void preloadTickets();

    BigDecimal refillAccount(long userId, BigDecimal moneyAmount);

    BigDecimal checkAccount(long userId);

    BigDecimal withdrawAccount(long userId, BigDecimal moneyAmount);

}
