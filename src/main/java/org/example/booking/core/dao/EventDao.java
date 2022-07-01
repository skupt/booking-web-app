package org.example.booking.core.dao;

import org.example.booking.core.model.EventImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventDao extends JpaRepository<EventImpl, Long> {

    @Query(value = "select * from event where date >= ?1 and date < ?2 limit ?3 offset ?4 ;", nativeQuery = true)
    List<EventImpl> findByDateRange(String fromDate, String toDate, int limit, int offset);
}
