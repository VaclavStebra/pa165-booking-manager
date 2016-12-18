package cz.muni.fi.pa165.brown.service;

import cz.muni.fi.pa165.brown.entity.Hotel;
import cz.muni.fi.pa165.brown.entity.Reservation;
import cz.muni.fi.pa165.brown.entity.Room;
import cz.muni.fi.pa165.brown.entity.User;
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;

/**
 * @author Peter Hutta
 */
public interface ReservationService {

    /**
     * Creates reservation
     *
     * @param reservation reservation to create
     * @return id of created reservation
     * @throws DataAccessException when execution fails
     */
    Long create(Reservation reservation) throws DataAccessException;

    /**
     * Updates reservation
     *
     * @param reservation reservation to update
     * @throws DataAccessException when execution fails
     */
    void update(Reservation reservation) throws DataAccessException;

    /**
     * Deletes reservation
     *
     * @param reservation reservation to delete
     * @throws DataAccessException when execution fails
     */
    void delete(Reservation reservation) throws DataAccessException;

    /**
     * Finds reservation with given id
     *
     * @param id id of a reservation
     * @return found reservation, null otherwise
     * @throws DataAccessException when execution fails
     */
    Reservation findById(Long id) throws DataAccessException;

    /**
     * Returns all reservations
     *
     * @return list of reservations
     * @throws DataAccessException when execution fails
     */
    List<Reservation> findAll() throws DataAccessException;

    /**
     * Returns reservations from the given period
     *
     * @param dateFrom initial date
     * @param dateTo   final date
     * @return List of reservations from the given period
     * @throws DataAccessException when execution fails
     */
    List<Reservation> findReservationsBetweenDates(Date dateFrom, Date dateTo) throws DataAccessException;

    /**
     * Returns reservations from last n days
     *
     * @param n number of days
     * @return List of reservations from last n days
     * @throws DataAccessException when execution fails
     */
    List<Reservation> findReservationsFromLastNDays(int n) throws DataAccessException;

    List<Room> findAvailableRooms(Hotel hotel, Date dateFrom, Date dateTo) throws DataAccessException;

    List<Reservation> findForUser(User user) throws DataAccessException;
}
