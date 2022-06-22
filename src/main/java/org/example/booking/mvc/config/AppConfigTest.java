package org.example.booking.mvc.config;

import org.example.booking.core.dao.EventDao;
import org.example.booking.core.dao.TicketDao;
import org.example.booking.core.dao.UserDao;
import org.example.booking.core.service.EventService;
import org.example.booking.core.service.TicketService;
import org.example.booking.core.service.UserService;
import org.example.booking.core.util.Storage;
import org.example.booking.mvc.facade.BookingWebAppFacade;
import org.example.booking.mvc.facade.impl.BookingWebAppFacadeImpl;
import org.example.booking.mvc.utils.PdfGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;


@Configuration
@PropertySource("classpath:application-test.properties")
@Profile("test")
public class AppConfigTest {

    @Value("${booking.core.storage.file}")
    private String storageFileName;

    @Value("${booking.mvc.xml.input.filename}")
    private String ticketsXmlInputFileName;

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Storage getStorage() throws IOException {
        String realFilePath = this.getClass().getClassLoader().getResource(storageFileName).getFile();
        Storage storage = new Storage();
        storage.setStorageFileName(realFilePath);
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
    @Qualifier("bookingFacade")
    BookingWebAppFacade getBookingFacade() {
        BookingWebAppFacadeImpl bookingWebAppFacade = new BookingWebAppFacadeImpl(getEventService(), getUserService(), getTicketService());
        bookingWebAppFacade.setBookingMvcXmlInputFilename(ticketsXmlInputFileName);
        return bookingWebAppFacade;
    }

    @Bean
    PdfGenerator getPdfGenerator() {
        PdfGenerator pdfGenerator = new PdfGenerator();
        return pdfGenerator;
    }
}
