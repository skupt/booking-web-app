package org.example.booking.mvc.config;

import org.example.booking.core.dao.EventDao;
import org.example.booking.core.dao.TicketDao;
import org.example.booking.core.dao.UserDao;
import org.example.booking.core.facade.BookingFacadeImpl;
import org.example.booking.core.service.EventService;
import org.example.booking.core.service.TicketService;
import org.example.booking.core.service.UserService;
import org.example.booking.core.util.Storage;
import org.example.booking.intro.facade.BookingFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;

import java.io.IOException;


@Configuration
@PropertySource("classpath:application-dev.properties")
@Profile("dev")
public class AppConfig {

    @Value("${booking.core.storage.file}")
    private String storageFileName;

    @Bean
    public Storage getStorage() throws IOException {
        String realFilePath = this.getClass().getClassLoader().getResource(storageFileName).getFile();
        Storage storage = new Storage();
        storage.setStorageFileName(realFilePath);
        storage.init();
        return storage;
    }

    @Bean
    public UserDao getUserDao() throws IOException {
        UserDao userDao = new UserDao();
        userDao.setStorage(getStorage());
        return userDao;
    }

    @Bean
    public EventDao getEventDao() throws IOException {
        EventDao eventDao = new EventDao();
        eventDao.setStorage(getStorage());
        return eventDao;
    }

    @Bean
    public TicketDao getTicketDao() throws IOException {
        TicketDao ticketDao = new TicketDao();
        ticketDao.setStorage(getStorage());
        return ticketDao;
    }

    @Bean
    public UserService getUserService() {
        return new UserService();
    }

    @Bean
    public EventService getEventService() {
        return new EventService();
    }

    @Bean
    TicketService getTicketService() {
        return new TicketService();
    }

    @Bean
    BookingFacade getBookingFacade() {
        return new BookingFacadeImpl(getEventService(), getUserService(), getTicketService());
    }
}
