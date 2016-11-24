package cz.muni.fi.pa165.brown.service.impl;

import cz.muni.fi.pa165.brown.service.ReservationService;
import cz.muni.fi.pa165.brown.service.TimeService;
import cz.muni.fi.pa165.brown.dao.ReservationDao;
import cz.muni.fi.pa165.brown.entity.Reservation;
import cz.muni.fi.pa165.brown.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Peter Hutta
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    private final static Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private TimeService timeService;

    @Override
    public Long create(Reservation reservation) throws ServiceException {
        try {
            reservationDao.create(reservation);
            return reservation.getId();
        } catch (Throwable ex) {
            String message = "Couldn't create reservation: " + reservation;
            logger.error(message, ex);
            throw new ServiceException(message, ex);
        }
    }

    @Override
    public void update(Reservation reservation) throws ServiceException {
        try {
            reservationDao.update(reservation);
        } catch (Throwable ex) {
            String message = "Couldn't update reservation: " + reservation;
            logger.error(message, ex);
            throw new ServiceException(message, ex);
        }
    }

    @Override
    public void delete(Reservation reservation) throws ServiceException {
        try {
            reservationDao.delete(reservation);
        } catch (Throwable ex) {
            String message = "Couldn't delete reservation: " + reservation;
            logger.error(message, ex);
            throw new ServiceException(message, ex);
        }
    }

    @Override
    public Reservation findById(Long id) throws ServiceException {
        try {
            return reservationDao.findById(id);
        } catch (Throwable ex) {
            String message = "Couldn't find reservation with id=" + id;
            logger.error(message, ex);
            throw new ServiceException(message, ex);
        }
    }

    @Override
    public List<Reservation> findAll() throws ServiceException {
        try {
            return reservationDao.findAll();
        } catch (Throwable ex) {
            String message = "Couldn't get all the reservations";
            logger.error(message, ex);
            throw new ServiceException(message, ex);
        }
    }

    @Override
    public List<Reservation> findReservationsBetweenDates(Date dateFrom, Date dateTo) throws ServiceException {
        try {
            return reservationDao.findReservationsBetweenDates(dateFrom, dateTo);
        } catch (Throwable ex) {
            String message = "Couldn't get all the reservations between " + dateFrom +
                    " and " + dateTo;
            logger.error(message, ex);
            throw new ServiceException(message, ex);
        }
    }

    @Override
    public List<Reservation> findReservationsFromLastNDays(int n) throws ServiceException {
        try {
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(timeService.getCurrentTime());
            calendar.add(Calendar.DAY_OF_YEAR, -n);
            Date lastWeek = calendar.getTime();

            return reservationDao.findReservationsBetweenDates(lastWeek, timeService.getCurrentTime());
        } catch (Throwable ex) {
            String message = "Couldn't get all the reservations from last " + n + " days";
            logger.error(message, ex);
            throw new ServiceException(message, ex);
        }
    }
}
