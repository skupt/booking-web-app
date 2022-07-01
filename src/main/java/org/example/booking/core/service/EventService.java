package org.example.booking.core.service;

import org.example.booking.core.dao.EventDao;
import org.example.booking.core.model.EventImpl;
import org.example.booking.intro.model.Event;
import org.example.booking.mvc.utils.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    DateFormatter dateFormatter;

    @Autowired
    private EventDao eventDao;

    public EventImpl getEventById(long eventId) {
        return eventDao.findById(eventId).orElse(null);
    }

    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        EventImpl event = new EventImpl();
        event.setTitle(title);
        ExampleMatcher ignorePropsMatcher = ExampleMatcher.matchingAny()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withIgnorePaths("id");
        return convertToEventList(eventDao.findAll(Example.of(event, ignorePropsMatcher), PageRequest.of(pageNum, pageSize)).toList());
    }

    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        int offset = pageNum * pageSize;
        int limit = pageSize;
        LocalDate passed = LocalDate.of(day.getYear() + 1900, day.getMonth() + 1, day.getDate());
        String from = passed.getYear() + "-" + passed.getMonthValue() + "-" + passed.getDayOfMonth();
        LocalDate dayAfter = passed.plusDays(1);
        String to = dayAfter.getYear() + "-" + dayAfter.getMonthValue() + "-" + (dayAfter.getDayOfMonth());
        return convertToEventList(eventDao.findByDateRange(from, to, limit, offset));
    }

    public Event createEvent(Event event) {
        EventImpl eventImpl = (EventImpl) event;
        return (Event) eventDao.save(eventImpl);
    }

    public Event updateEvent(Event event) {
        EventImpl eventImpl = (EventImpl) event;
        return eventDao.save(eventImpl);
    }

    public boolean deleteEvent(long eventId) {
        eventDao.deleteById(eventId);
        return true;
    }

    private List<Event> convertToEventList(List<EventImpl> eventImplList) {
        return eventImplList.stream()
                .map(eventImpl -> (Event) eventImpl)
                .collect(Collectors.toList());
    }
}