package integrationtests;

import org.example.booking.BookingWebappApplication;
import org.example.booking.core.facade.BookingFacadeImpl;
import org.example.booking.intro.model.User;
import org.example.booking.mvc.controllers.EventsController;
import org.example.booking.mvc.controllers.UsersController;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
import java.text.ParseException;

@SpringJUnitConfig(classes = {BookingWebappApplication.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllersTest {
    @Autowired
    private UsersController usersController;
    @Autowired
    private EventsController eventsController;
    @Autowired
    private BookingFacadeImpl bookingFacade;

    @MockBean
    private RedirectAttributes redirectAttributes;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void UsersControllerCreateUser() {
        RedirectAttributes attrs = redirectAttributes;
        String actual = usersController.createUser("User", "email@test", redirectAttributes);

        Assertions.assertEquals(actual, "redirect:/success");
    }

    @Test
    public void EventsControllerCreateUser() throws ParseException {
        RedirectAttributes attrs = redirectAttributes;
        RedirectView actual = eventsController.createEvent("Cinema", "2000-01-01", 0, 0, BigDecimal.valueOf(13), redirectAttributes);
        actual.getUrl();

        Assertions.assertEquals("/success", actual.getUrl());
    }

    @Test
    public void shouldReturnUserWithIdMinus1() {
        User user = bookingFacade.getUserById(-1);
        String expected = "UserImpl{id=-1, name='Vasya', email='e@e'}";
        Assertions.assertEquals(expected, user.toString());
    }

}
