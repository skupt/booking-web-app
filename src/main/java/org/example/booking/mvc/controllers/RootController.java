package org.example.booking.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

    @GetMapping({"/", "index.html"})
    public String getIndex() {
        return "index.html";
    }

    @RequestMapping("success")
    public String dataModificationSuccess() {
        return "success.html";
    }

    @RequestMapping("inform")
    public String inform() {
        return "inform.html";
    }

}
