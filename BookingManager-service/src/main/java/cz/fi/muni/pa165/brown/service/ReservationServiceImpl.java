package cz.fi.muni.pa165.brown.service;

import cz.muni.fi.pa165.brown.dao.ReservationDao;
import cz.muni.fi.pa165.brown.entity.Reservation;
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

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private TimeService timeService;

    @Override
    public Long create(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Parameter reservation is null");
        }
        reservationDao.create(reservation);
        return reservation.getId();
    }

    @Override
    public void update(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Parameter reservation is null");
        }
        reservationDao.update(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Parameter reservation is null");
        }
        reservationDao.delete(reservation);
    }

    @Override
    public Reservation findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Parameter id is null");
        }
        return reservationDao.findById(id);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationDao.findAll();
    }

    @Override
    public List<Reservation> findReservationsBetweenDates(Date dateFrom, Date dateTo) {
        if (dateFrom == null) {
            throw new IllegalArgumentException("Parameter dateFrom is null");
        }
        if (dateTo == null) {
            throw new IllegalArgumentException("Parameter dateTo is null");
        }
        return reservationDao.findReservationsBetweenDates(dateFrom, dateTo);
    }

    @Override
    public List<Reservation> findReservationsFromLastNDays(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Parameter n isn't positive number");
        }
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(timeService.getCurrentTime());
        calendar.add(Calendar.DAY_OF_YEAR, -n);
        Date lastWeek = calendar.getTime();

        return reservationDao.findReservationsBetweenDates(lastWeek, timeService.getCurrentTime());
    }
}
