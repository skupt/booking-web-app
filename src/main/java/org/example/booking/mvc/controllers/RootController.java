package org.example.booking.mvc.controllers;

import org.example.booking.intro.facade.BookingFacade;
import org.example.booking.intro.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RootController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping({"/", "index.html"})
    public String getIndex() {
        return "index.html";
    }

    @GetMapping("test")
    public String getEvents(Model model) {
        List<Event> eventList = bookingFacade.getEventsByTitle("C", 10, 1);
        model.addAttribute("events", eventList);
        return "test.html";
    }

    @RequestMapping("success")
    public String dataModificationSuccess() {
        return "success.html";
    }



}
