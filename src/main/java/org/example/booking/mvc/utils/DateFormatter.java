package org.example.booking.mvc.utils;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class DateFormatter implements Formatter<Date> {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public DateFormatter() {
        super();
    }

    public Date parse(String text, Locale locale) throws ParseException {
        SimpleDateFormat dateFormat = createDateFormat();
        return dateFormat.parse(text);
    }

    public String print(Date object, Locale locale) {
        SimpleDateFormat dateFormat = createDateFormat();
        return dateFormat.format(object);
    }

    private SimpleDateFormat createDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setLenient(false);
        return dateFormat;
    }
}