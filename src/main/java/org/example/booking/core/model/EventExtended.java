package org.example.booking.core.model;

import org.example.booking.intro.model.Event;

import java.math.BigDecimal;

public interface EventExtended extends Event {
    BigDecimal getPrice();

    void setPrice(BigDecimal price);

}
