package cz.muni.fi.pa165.brown.dao;

import cz.muni.fi.pa165.brown.entity.Reservation;

import java.util.Date;
import java.util.List;

/**
 * @author Peter Hutta
 * @version 2.0  22.11.2016
 */
public interface ReservationDao {

    /**
     * Creates reservation in the database
     * @param reservation Reservation to create
     */
    void create(Reservation reservation);

    /**
     * Updates reservation in the database
     * @param reservation Reservation to update
     */
    void update(Reservation reservation);

    /**
     * Deletes reservation from the database
     * @param reservation Reservation to delete
     */
    void delete(Reservation reservation);

    /**
     * Finds reservation with given id
     * @param id id of reservation
     * @return Found reservation, null if not found
     */
    Reservation findById(Long id);

    /**
     * Returns list of all reservations
     * @return List of all reservations
     */
    List<Reservation> findAll();

    /**
     * Returns reservations from the given period
     * @param dateFrom initial date
     * @param dateTo final date
     * @return List of reservations from the given period
     */
    List<Reservation> findReservationsBetweenDates(Date dateFrom, Date dateTo);
}
