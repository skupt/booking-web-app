package org.example.booking.mvc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:application-dev.properties")
@Profile("dev")
public class AppConfig {

    @Value("${booking.core.storage.file}")
    private String storageFileName;

    @Value("${booking.mvc.xml.input.filename}")
    private String ticketsXmlInputFileName;

}
