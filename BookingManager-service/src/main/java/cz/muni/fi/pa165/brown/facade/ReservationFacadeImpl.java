package cz.muni.fi.pa165.brown.facade;

import cz.muni.fi.pa165.brown.BeanMappingService;
import cz.muni.fi.pa165.brown.dto.hotel.HotelDTO;
import cz.muni.fi.pa165.brown.dto.room.RoomDTO;
import cz.muni.fi.pa165.brown.dto.user.UserDTO;
import cz.muni.fi.pa165.brown.entity.Hotel;
import cz.muni.fi.pa165.brown.entity.Room;
import cz.muni.fi.pa165.brown.entity.User;
import cz.muni.fi.pa165.brown.service.ReservationService;
import cz.muni.fi.pa165.brown.dto.reservation.ReservationDTO;
import cz.muni.fi.pa165.brown.entity.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @author Peter Hutta
 */
@Service
@Transactional
public class ReservationFacadeImpl implements ReservationFacade {

    private final static Logger logger = LoggerFactory.getLogger(ReservationFacadeImpl.class);

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private ReservationService reservationService;

    @Override
    public void create(ReservationDTO reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("reservation dto is null");
        }
        try {
            Reservation res = beanMappingService.mapTo(reservation, Reservation.class);
            reservationService.create(res);
            reservation.setId(res.getId());
        } catch (DataAccessException ex) {
            logger.warn(ex.getMessage(), ex);
        }
    }

    @Override
    public void update(ReservationDTO reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("reservation dto is null");
        }
        try {
            reservationService.update(beanMappingService.mapTo(reservation, Reservation.class));
        } catch (DataAccessException ex) {
            logger.warn(ex.getMessage(), ex);
        }
    }

    @Override
    public void delete(ReservationDTO reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("reservation dto is null");
        }
        try {
            reservationService.delete(beanMappingService.mapTo(reservation, Reservation.class));
        } catch (DataAccessException ex) {
            logger.warn(ex.getMessage(), ex);
        }
    }

    @Override
    public List<ReservationDTO> findAll() {
        try {
            return beanMappingService.mapTo(reservationService.findAll(), ReservationDTO.class);
        } catch (DataAccessException ex) {
            logger.warn(ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public ReservationDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        try {
            Reservation reservation = reservationService.findById(id);
            return (reservation == null) ? null :
                    beanMappingService.mapTo(reservation, ReservationDTO.class);
        } catch (DataAccessException ex) {
            logger.warn(ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public List<ReservationDTO> findReservationsBetweenDates(Date dateFrom, Date dateTo) {
        if (dateFrom == null) {
            throw new IllegalArgumentException("dateFrom is null");
        }
        if (dateTo == null) {
            throw new IllegalArgumentException("dateTo is null");
        }
        try {
            return beanMappingService.mapTo(
                    reservationService.findReservationsBetweenDates(dateFrom, dateTo),
                    ReservationDTO.class);
        } catch (DataAccessException ex) {
            logger.warn(ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public List<ReservationDTO> findReservationsFromLastWeek() {
        try {
            return beanMappingService.mapTo(
                    reservationService.findReservationsFromLastNDays(7), ReservationDTO.class);
        } catch (DataAccessException ex) {
            logger.warn(ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public List<RoomDTO> findAvailableRooms(HotelDTO hotel, Date dateFrom, Date dateTo) {
        if (hotel == null) {
            throw new IllegalArgumentException("hotel dto is null");
        }
        if (dateFrom == null) {
            throw new IllegalArgumentException("dateFrom is null");
        }
        if (dateTo == null) {
            throw new IllegalArgumentException("dateTo is null");
        }
        Hotel desiredHotel = beanMappingService.mapTo(hotel, Hotel.class);
        List<Room> rooms = reservationService.findAvailableRooms(desiredHotel, dateFrom, dateTo);
        return beanMappingService.mapTo(rooms, RoomDTO.class);
    }

    @Override
    public List<ReservationDTO> findForUser(UserDTO user) {
        if (user == null) {
            throw new IllegalArgumentException("user dto is null");
        }
        try {
            User userEntity = beanMappingService.mapTo(user, User.class);
            return beanMappingService.mapTo(
                    reservationService.findForUser(userEntity), ReservationDTO.class
            );
        } catch (DataAccessException ex) {
            logger.warn(ex.getMessage(), ex);
        }
        return null;
    }
}
