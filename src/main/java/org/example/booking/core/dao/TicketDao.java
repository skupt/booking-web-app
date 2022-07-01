package org.example.booking.core.dao;

import org.example.booking.core.model.TicketImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketDao extends JpaRepository<TicketImpl, Long> {

    @Query(value = "select count(*) from ticket where event_id = ?1 and category = ?3 and place = ?2", nativeQuery = true)
    int ticketsOnPlace(long eventId, int place, String category);

    @Query(value = "select * from ticket where user_id = ?1 limit ?2 offset ?3", nativeQuery = true)
    List<TicketImpl> findAllByUser(long userId, int size, int offset);

    @Query(value = "select * from ticket where event_id = ?1 limit ?2 offset ?3", nativeQuery = true)
    List<TicketImpl> findAllByEvent(long eventId, int size, int offset);

}
