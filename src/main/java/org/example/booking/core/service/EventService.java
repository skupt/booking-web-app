package org.example.booking.core.service;

import org.example.booking.core.dao.EventDao;
import org.example.booking.core.model.EventImpl;
import org.example.booking.intro.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventDao eventDao;

    public Event getEventById(long eventId) {
        return eventDao.findById(eventId).orElse(null);
    }

    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        EventImpl event = new EventImpl();
        event.setTitle(title);
        return convertToEventList(eventDao.findAll(Example.of(event), PageRequest.of(pageNum, pageSize)).toList());
    }

    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        EventImpl eventExample = new EventImpl();
        eventExample.setDate(day);
        eventDao.findAll(Example.of(eventExample), PageRequest.of(pageNum, pageSize)).toList();
        return convertToEventList(eventDao.findAll(Example.of(eventExample), PageRequest.of(pageNum, pageSize)).toList());
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