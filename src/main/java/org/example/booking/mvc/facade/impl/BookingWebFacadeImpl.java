package org.example.booking.mvc.facade.impl;

import org.example.booking.core.service.AccountService;
import org.example.booking.mvc.facade.BookingWebAppFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BookingWebFacadeImpl implements BookingWebAppFacade {

    public static final Logger LOGGER = LoggerFactory.getLogger(BookingWebFacadeImpl.class);

    private AccountService accountService;

    public BookingWebFacadeImpl(AccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    public void preloadTickets() {
        throw new UnsupportedOperationException();
    }

    @Override
    public BigDecimal refillAccount(long userId, BigDecimal moneyAmount) {
        return accountService.refill(userId, moneyAmount);
    }

    @Override
    public BigDecimal checkAccount(long userId) {
        return accountService.check(userId);
    }

    @Override
    public BigDecimal withdrawAccount(long userId, BigDecimal moneyAmount) {
        return accountService.withdraw(userId, moneyAmount);
    }

}