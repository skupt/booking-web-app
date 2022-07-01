package org.example.booking.mvc.controllers;

import org.example.booking.intro.model.Event;
import org.example.booking.intro.model.Ticket;
import org.example.booking.intro.model.User;
import org.example.booking.mvc.facade.BookingWebAppFacade;
import org.example.booking.mvc.utils.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("tickets")
public class TicketsController {

    @Autowired
    private BookingWebAppFacade bookingFacade;
    @Autowired
    private PdfGenerator pdfGenerator;

    @GetMapping("bookTicketForm")
    public String bookTicketForm(Model model) {
        model.addAttribute("categories", Arrays.stream(Ticket.Category.values()).map(c -> c.name()).collect(Collectors.toList()));
        return "tickets/bookTicketForm.html";
    }

    @PostMapping("/")
    public String bookTicket(@RequestParam("userId") long userId, @RequestParam("eventId") long eventId,
                             @RequestParam("place") int place, @RequestParam("category") String category,
                             RedirectAttributes redirectAttributes) {
        Ticket ticket = bookingFacade.bookTicket(userId, eventId, place, Ticket.Category.valueOf(category));
        redirectAttributes.addFlashAttribute("msg", ticket != null ? "Ticket is booked: " + ticket : "Ticket is not Booked, place is not free");
        return "redirect:/success";
    }

    @GetMapping("getBookedTicketsByUserForm")
    public String getBookedTicketsByUserForm() {
        return "tickets/getBookedTicketsByUserForm.html";
    }

    @GetMapping(value = "/", params = {"userId", "pageSize", "pageNumber"})
    public String getBookedTicketsByUser(@RequestParam("userId") long userId, @RequestParam("pageSize") int pageSize,
                                         @RequestParam("pageNumber") int pageNUmber, Model model) {
        User user = bookingFacade.getUserById(userId);
        if (user == null) throw new RuntimeException("Nothing to show. No event having this id: " + userId);
        List<Ticket> ticketList = bookingFacade.getBookedTickets(user, pageSize, pageNUmber);
        model.addAttribute("tickets", ticketList);
        return "tickets/getTickets.html";
    }

    @GetMapping("getBookedTicketsByEventForm")
    public String getBookedTicketsByEventForm() {
        return "tickets/getBookedTicketsByEventForm.html";
    }

    @GetMapping(value = "/", params = {"eventId", "pageSize", "pageNumber"})
    public String getBookedTicketsByEvent(@RequestParam("eventId") long eventId, @RequestParam("pageSize") int pageSize,
                                          @RequestParam("pageNumber") int pageNUmber, Model model) {
        Event event = bookingFacade.getEventById(eventId);
        if (event == null) throw new RuntimeException("Nothing to show. No event having this id: " + eventId);
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

    @GetMapping("getBookedTicketsByUserFormPdf")
    public String getBookedTicketsByUserFormPdf() {
        return "tickets/getBookedTicketsByUserFormPdf.html";
    }

    @GetMapping(value = "/pdf", headers = "accept=application/pdf", params = {"userId", "pageSize", "pageNumber"})
    public ResponseEntity<InputStreamResource> getBookedTicketsByUserPdf(@RequestParam("userId") long userId, @RequestParam("pageSize") int pageSize,
                                                                         @RequestParam("pageNumber") int pageNUmber) {
        User user = bookingFacade.getUserById(userId);
        List<Ticket> ticketList = bookingFacade.getBookedTickets(user, pageSize, pageNUmber);
        ByteArrayInputStream bais = pdfGenerator.ticketReport(ticketList);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "inline; filename=ticketReport.pdf");

        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bais));
    }

    @GetMapping("preloadXmlTicketsForm")
    public String preloadXmlTicketsForm() {
        return "tickets/preloadXmlTicketsForm.html";
    }

    @PostMapping("batchCreation")
    public String preloadXmlTickets(RedirectAttributes redirectAttributes) {
        try {
            bookingFacade.preloadTickets();
            redirectAttributes.addFlashAttribute("msg", "Tickets were preloaded.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("msg", "Tickets were not preloaded.");
        }
        return "redirect:/success.html";

    }
}
