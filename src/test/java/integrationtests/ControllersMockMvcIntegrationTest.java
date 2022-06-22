package integrationtests;

import org.example.booking.mvc.config.AppConfigTest;
import org.example.booking.mvc.config.ViewsConfig;
import org.example.booking.mvc.config.WebAppInitializer;
import org.example.booking.mvc.config.WebConfig;
import org.example.booking.mvc.controllers.RootController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(classes = {WebAppInitializer.class, WebConfig.class, ViewsConfig.class, AppConfigTest.class})
public class ControllersMockMvcIntegrationTest {

    MockMvc mockMvc;

//    @BeforeEach
//    void setup() {
//        this.mockMvc = MockMvcBuilders.standaloneSetup(new RootController()).build();
//    }

    @BeforeEach
    void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void rootControllerTest() throws Exception {
        mockMvc.perform(get("/")).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("index.html"));

    }
}
