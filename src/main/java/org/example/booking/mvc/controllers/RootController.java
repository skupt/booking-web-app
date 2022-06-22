package org.example.booking.mvc.controllers;

import org.example.booking.intro.facade.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping({"/", "index.html"})
    public String getIndex() {
        return "index.html";
    }

    @RequestMapping("success")
    public String dataModificationSuccess() {
        return "success.html";
    }


}
