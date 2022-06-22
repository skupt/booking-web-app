package integrationtests;

import org.example.booking.intro.model.User;
import org.example.booking.mvc.config.AppConfigTest;
import org.example.booking.mvc.facade.impl.BookingWebAppFacadeImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = {AppConfigTest.class})
public class BookingWebAppFacadeImplTest {
    @Autowired
    private BookingWebAppFacadeImpl bookingWebAppFacade;

    @Test
    public void shouldReturnUserWithId12345() {
        User user = bookingWebAppFacade.getUserById(12345);
        String expected = "UserImpl{id=12345, name='Test', email='email@emaol.com'}";
        Assertions.assertEquals(expected, user.toString());
    }
}
