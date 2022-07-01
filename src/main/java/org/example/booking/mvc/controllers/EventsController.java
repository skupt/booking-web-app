package org.example.booking.mvc.controllers;

import org.example.booking.core.model.EventImpl;
import org.example.booking.intro.facade.BookingFacade;
import org.example.booking.intro.model.Event;
import org.example.booking.mvc.utils.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventsController {

    @Autowired
    private BookingFacade bookingFacade;
    @Autowired
    private DateFormatter dateFormatter;

    @GetMapping("getEventByIdForm")
    public String getEventByIdForm() {
        return "events/getEventByIdForm.html";
    }

    @GetMapping(value = "/", params = "id")
    public String getEventById(@RequestParam("id") long id, Model model) {
        Event event = bookingFacade.getEventById(id);
        model.addAttribute("event", event);
        return "events/getEventById.html";
    }

    @GetMapping("getEventsByTitleForm")
    public String getEventsByTitleForm() {
        return "events/getEventsByTitleForm.html";
    }

    @GetMapping(value = "/", params = {"title", "pageSize", "pageNumber"})
    public String getEventsByTitle(@RequestParam("title") String title,
                                   @RequestParam("pageSize") int pageSize,
                                   @RequestParam("pageNumber") int pageNumber, Model model) {
        List<Event> eventList = bookingFacade.getEventsByTitle(title, pageSize, pageNumber);
        model.addAttribute("events", eventList);

        return "events/getEventsByTitle.html";
    }

    @GetMapping("getEventsForDayForm")
    public String getEventsForDayForm() {
        return "events/getEventsForDayForm.html";
    }

    @GetMapping(value = "/", params = {"date", "pageSize", "pageNumber"})
    public String getEventsForDay(@RequestParam("date") String date, @RequestParam("pageSize") int pageSize,
                                  @RequestParam("pageNumber") int pageNumber, Model model) throws ParseException {
        Date dayFor = dateFormatter.parse(date, null);
        List<Event> eventList = bookingFacade.getEventsForDay(dayFor, pageSize, pageNumber);
        model.addAttribute("events", eventList);

        return "events/getEventsForDay.html";
    }

    @GetMapping("createEventForm")
    public String createEventForm() {
        return "events/createEventForm.html";
    }

    @PostMapping(value = "/", params = {"title", "date", "hour", "minute", "price"})
    public RedirectView createEvent(@RequestParam("title") String title, @RequestParam("date") String date,
                                    @RequestParam("hour") int hour, @RequestParam("minute") int minute,
                                    @RequestParam("price") BigDecimal price, RedirectAttributes redirectAttributes) throws ParseException {

        Date parsedDate = dateFormatter.parse(date, null);
        parsedDate.setHours(hour);
        parsedDate.setMinutes(minute);
        EventImpl event = new EventImpl();
        event.setDate(parsedDate);
        event.setTitle(title);
        event.setPrice(price);
        Event createdEvent = bookingFacade.createEvent(event);
        redirectAttributes.addFlashAttribute("msg", "Event created." + createdEvent);

        return new RedirectView("/success", true);
    }

    @GetMapping("updateEventForm")
    public String updateEventForm() {
        return "events/updateEventForm.html";
    }

    @PutMapping(value = "/", params = {"id", "title", "date", "hour", "minute"})
    public RedirectView updateEvent(@RequestParam("id") int id, @RequestParam("title") String title,
                                    @RequestParam("date") String date, @RequestParam("hour") int hour,
                                    @RequestParam("minute") int minute, RedirectAttributes redirectAttributes) throws ParseException {
        Date parsedDate = dateFormatter.parse(date, null);
        parsedDate.setHours(hour);
        parsedDate.setMinutes(minute);
        Event event = new EventImpl();
        event.setId(id);
        event.setDate(parsedDate);
        event.setTitle(title);
        Event updatedEvent = bookingFacade.updateEvent(event);
        redirectAttributes.addFlashAttribute("msg", "Event_updated.");

        return new RedirectView("/success", true);
    }

    @GetMapping("deleteEventForm")
    public String deleteEventForm() {
        return "events/deleteEventForm.html";
    }

    @DeleteMapping(value = "/", params = "id")
    public String deleteEvent(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        boolean result = bookingFacade.deleteEvent(id);
        redirectAttributes.addFlashAttribute("msg", result == true ? "Event was deleted." : "Event was not deleted.");

        return "redirect:/success";
    }

}
