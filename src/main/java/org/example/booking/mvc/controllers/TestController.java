package org.example.booking.mvc.controllers;

import org.example.booking.intro.facade.BookingFacade;
import org.example.booking.intro.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
//@RequestMapping("test")
public class TestController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("events")
    public String getEvents(Model model) {
        List<Event> eventList = bookingFacade.getEventsByTitle("C", 10, 1);
        model.addAttribute("events", eventList);
        return "testevents.html";
    }


}
