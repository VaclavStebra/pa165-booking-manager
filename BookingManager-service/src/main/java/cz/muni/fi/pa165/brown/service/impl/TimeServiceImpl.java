package cz.muni.fi.pa165.brown.service.impl;

import cz.muni.fi.pa165.brown.service.TimeService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Peter Hutta
 */
@Service
public class TimeServiceImpl implements TimeService {
    @Override
    public Date getCurrentTime() {
        return new Date();
    }
}
