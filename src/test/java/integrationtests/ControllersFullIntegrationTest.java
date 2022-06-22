package integrationtests;

import org.example.booking.mvc.config.AppConfigTest;
import org.example.booking.mvc.config.ViewsConfig;
import org.example.booking.mvc.config.WebAppInitializer;
import org.example.booking.mvc.config.WebConfig;
import org.example.booking.mvc.controllers.RootController;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.web.context.WebApplicationContext;

@SpringJUnitWebConfig(classes = {WebAppInitializer.class, WebConfig.class, ViewsConfig.class, AppConfigTest.class})
public class ControllersFullIntegrationTest {
    @Autowired
    private RootController rootController;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void rootControllerTest() throws Exception {
        assertNotNull(webApplicationContext);
        assertEquals("success.html", rootController.getIndex());
    }
}
