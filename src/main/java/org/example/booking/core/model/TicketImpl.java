package org.example.booking.core.model;

import lombok.Data;
import org.example.booking.intro.model.Ticket;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ticket")
public class TicketImpl implements Ticket, Cloneable, Comparable<TicketImpl> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventImpl event;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserImpl user;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "place")
    private int place;

    public TicketImpl() {
    }

    public TicketImpl(long id, EventImpl event, UserImpl user, Category category, int place) {
        this.id = id;
        this.event = event;
        this.user = user;
        this.category = category;
        this.place = place;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getEventId() {
        return event.getId();
    }

    @Override
    public void setEventId(long eventId) {
        this.event.setId(eventId);
    }

    @Override
    public long getUserId() {
        return user.getId();
    }

    @Override
    public void setUserId(long userId) {
        this.user.setId(userId);
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int getPlace() {
        return place;
    }

    @Override
    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "TicketImpl{" +
                "id=" + id +
                ", eventId=" + event.getId() +
                ", userId=" + user.getId() +
                ", category=" + category +
                ", place=" + place +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(TicketImpl o) {
        return o.getEvent().getDate().compareTo(this.event.getDate());
    }
}
