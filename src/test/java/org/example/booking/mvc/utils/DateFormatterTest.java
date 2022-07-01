package org.example.booking.mvc.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

public class DateFormatterTest {

    @Test
    public void parseTest() throws ParseException {
        String dateString = "2020-06-22";
        DateFormatter dateFormatter = new DateFormatter();

        Date actual = dateFormatter.parse(dateString, null);

        Assertions.assertEquals(120, actual.getYear());
        Assertions.assertEquals(5, actual.getMonth());
        Assertions.assertEquals(22, actual.getDate());
    }
}
