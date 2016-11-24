package cz.fi.muni.pa165.brown.service;

import cz.muni.fi.pa165.brown.entity.Reservation;

import java.util.Date;
import java.util.List;

/**
 * @author Peter Hutta
 */
public interface ReservationService {

    /**
     * Creates reservation
     * @param reservation reservation to create
     * @return id of created reservation
     */
    Long create(Reservation reservation);

    /**
     * Updates reservation
     * @param reservation reervation to update
     */
    void update(Reservation reservation);

    /**
     * Deletes reservation
     * @param reservation reservation to delete
     */
    void delete(Reservation reservation);

    /**
     * Finds reservation with given id
     * @param id id of a reservation
     * @return found reservation, null otherwise
     */
    Reservation findById(Long id);

    /**
     * Returns all reservations
     * @return list of reservations
     */
    List<Reservation> findAll();

    /**
     * Returns reservations from the given period
     * @param dateFrom initial date
     * @param dateTo final date
     * @return List of reservations from the given period
     */
    List<Reservation> findReservationsBetweenDates(Date dateFrom, Date dateTo);

    /**
     * Returns reservations from last n days
     * @param n number of days
     * @return List of reservations from last n days
     */
    List<Reservation> findReservationsFromLastNDays(int n);
}
