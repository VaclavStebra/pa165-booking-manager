package cz.muni.fi.pa165.brown.service.impl;

import cz.muni.fi.pa165.brown.BeanMappingService;
import cz.muni.fi.pa165.brown.dto.HotelDTO;
import cz.muni.fi.pa165.brown.entity.Hotel;
import cz.muni.fi.pa165.brown.entity.Room;
import cz.muni.fi.pa165.brown.service.ReservationService;
import cz.muni.fi.pa165.brown.service.RoomService;
import cz.muni.fi.pa165.brown.service.TimeService;
import cz.muni.fi.pa165.brown.dao.ReservationDao;
import cz.muni.fi.pa165.brown.entity.Reservation;
import cz.muni.fi.pa165.brown.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private RoomService roomService;

    @Autowired
    private BeanMappingService beanMappingService;

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
        if (n < 0) {
            String message = "Parameter n is negative";
            logger.error(message);
            throw new IllegalArgumentException(message);
        }
        try {
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(timeService.getCurrentTime());
            calendar.add(Calendar.DAY_OF_YEAR, -n);
            Date past = calendar.getTime();

            return reservationDao.findReservationsBetweenDates(past, timeService.getCurrentTime());
        } catch (Throwable ex) {
            String message = "Couldn't get all the reservations from last " + n + " days";
            logger.error(message, ex);
            throw new ServiceException(message, ex);
        }
    }

    @Override
    public List<Room> findAvailableRooms(Hotel hotel, Date dateFrom, Date dateTo) throws DataAccessException {
        try {
            List<Room> hotelRooms = roomService.findByHotel(hotel);
            List<Reservation> reservations = findReservationsBetweenDates(dateFrom, dateTo);
            List<Room> availableRooms = new ArrayList<>();
            for (Room room : hotelRooms) {
                boolean reserved = false;
                for (Reservation reservation : reservations) {
                    if (reservation.getRoom().equals(room)) {
                        reserved = true;
                        break;
                    }
                }
                if ( ! reserved) {
                    availableRooms.add(room);
                }
            }
            return availableRooms;
        } catch (Throwable t) {
            String message = "Couldn't get available rooms";
            logger.error(message, t);
            throw new ServiceException(message, t);
        }
    }
}
