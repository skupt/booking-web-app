package org.example.booking.mvc.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BookingHandlerExceptionResolver implements HandlerExceptionResolver, Ordered {
    private final String DEFAULT_ERROR_VIEW = "booking_error.html";
    private final Logger LOGGER = LoggerFactory.getLogger(BookingHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
        LOGGER.warn(e.getMessage() + ". " + e.getCause());
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", e.getMessage());
        mav.addObject("exception", e);
        mav.addObject("url", httpServletRequest.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @Override
    public int getOrder() {
        return 2147483646;
    }
}
