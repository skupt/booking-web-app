package org.example.booking.mvc.controllers;

import org.example.booking.intro.facade.BookingFacade;
import org.example.booking.intro.model.Event;
import org.example.booking.intro.model.Ticket;
import org.example.booking.intro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("tickets")
public class TicketsController {
    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("bookTicketForm")
    public String bookTicketForm(Model model) {
        model.addAttribute("categories", Arrays.stream(Ticket.Category.values()).map(c->c.name()).collect(Collectors.toList()));
        return "tickets/bookTicketForm.html";
    }

    @PostMapping("/")
    public String bookTicket(@RequestParam("userId") long userId, @RequestParam("eventId") long eventId,
                             @RequestParam("place") int place, @RequestParam("category") String category,
                             RedirectAttributes redirectAttributes) {
        Ticket ticket = bookingFacade.bookTicket(userId, eventId, place, Ticket.Category.valueOf(category));
        redirectAttributes.addFlashAttribute("msg", ticket != null ? "Ticket is booked: " + ticket.toString() : "Ticket is not Booked");
        return "redirect:/success.html";
    }

    @GetMapping("getBookedTicketsByUserForm")
    public String getBookedTicketsByUserForm() {
        return "tickets/getBookedTicketsByUserForm.html";
    }

    @GetMapping(value="/", params = {"userId", "pageSize", "pageNumber"})
    public String getBookedTicketsByUser(@RequestParam("userId") long userId, @RequestParam("pageSize") int pageSize,
                                         @RequestParam("pageNumber") int pageNUmber, Model model) {
        User user = bookingFacade.getUserById(userId);
        List<Ticket> ticketList = bookingFacade.getBookedTickets(user, pageSize, pageNUmber);
        model.addAttribute("tickets", ticketList);
        return "tickets/getTickets.html";
    }

    @GetMapping("getBookedTicketsByEventForm")
    public String getBookedTicketsByEventForm() {
        return "tickets/getBookedTicketsByEventForm.html";
    }

    @GetMapping(value="/", params = {"eventId", "pageSize", "pageNumber"})
    public String getBookedTicketsByEvent(@RequestParam("eventId") long eventId, @RequestParam("pageSize") int pageSize,
                                         @RequestParam("pageNumber") int pageNUmber, Model model) {
        Event event = bookingFacade.getEventById(eventId);
        List<Ticket> ticketList = bookingFacade.getBookedTickets(event, pageSize, pageNUmber);
        model.addAttribute("tickets", ticketList);
        return "tickets/getTickets.html";
    }

    @GetMapping("cancelTicketForm")
    public String cancelTicketForm() {
        return "tickets/cancelTicketForm.html";
    }

    @DeleteMapping("/")
    public String cancelTicket(@RequestParam("ticketId") int ticketId, RedirectAttributes redirectAttributes) {
        boolean deleted = bookingFacade.cancelTicket(ticketId);
        redirectAttributes.addFlashAttribute("msg", deleted ? "Ticket was cancelled (id: " + ticketId + ")." : "Ticket was not cancelled");
        return "redirect:/success";
    }


}
