package org.example.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BookingWebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingWebappApplication.class, args);
    }

}
