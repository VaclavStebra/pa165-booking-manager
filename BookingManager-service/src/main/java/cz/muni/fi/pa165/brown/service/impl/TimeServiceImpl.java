package cz.muni.fi.pa165.brown.service.impl;

import cz.muni.fi.pa165.brown.service.TimeService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author Peter Hutta
 */
@Service
public class TimeServiceImpl implements TimeService {
    @Override
    public Date getCurrentTime() {
        return Date.from(ZonedDateTime.now().toInstant());
    }
}
