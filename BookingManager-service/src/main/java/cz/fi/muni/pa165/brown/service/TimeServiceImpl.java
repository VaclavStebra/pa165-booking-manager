package cz.fi.muni.pa165.brown.service;

import java.util.Date;

/**
 * @author Peter Hutta
 */
public class TimeServiceImpl implements TimeService {
    @Override
    public Date getCurrentTime() {
        return new Date();
    }
}
