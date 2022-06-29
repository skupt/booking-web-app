package org.example.booking.core.dao;

import org.example.booking.core.model.EventImpl;
import org.example.booking.intro.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventDao extends JpaRepository<EventImpl, Long> {

//    @Query("select e from EventImpl e where e.id = ?1")
//    Optional<EventImpl> findById(Long eventId);
//
//    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);
//
//    List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
//        return storage.getEventsForDay(day, pageSize, pageNum);
//    }
//
//    Event createEvent(Event event) {
//        return storage.createEvent(event);
//    }
//
//    Event updateEvent(Event event) {
//        return storage.updateEvent(event);
//    }
//
//    boolean deleteEvent(long eventId) {
//        return storage.deleteEvent(eventId);
//    }
}
