package cz.muni.fi.pa165.brown.facade;

import cz.muni.fi.pa165.brown.dto.hotel.HotelDTO;
import cz.muni.fi.pa165.brown.dto.reservation.ReservationDTO;
import cz.muni.fi.pa165.brown.dto.room.RoomDTO;
import cz.muni.fi.pa165.brown.dto.user.UserDTO;

import java.util.Date;
import java.util.List;

/**
 * @author Peter Hutta
 */
public interface ReservationFacade {

    /**
     * Creates reservation
     * @param reservation reservation to create
     */
    void create(ReservationDTO reservation);

    /**
     * Updates reservation
     * @param reservation reservation to update
     */
    void update(ReservationDTO reservation);

    /**
     * Deletes reservation
     * @param reservation reservation to delete
     */
    void delete(ReservationDTO reservation);

    /**
     * Returns all reservations
     * @return list of all reservations
     */
    List<ReservationDTO> findAll();

    /**
     * Returns reservation with given Id
     * @param id id of reservation
     * @return found reservation, null otherwise
     */
    ReservationDTO findById(Long id);

    /**
     * Returns reservations from the given period
     * @param dateFrom initial date
     * @param dateTo final date
     * @return List of reservations from the given period
     */
    List<ReservationDTO> findReservationsBetweenDates(Date dateFrom, Date dateTo);

    /**
     * Returns reservations from the last week
     * @return List of reservations from the last week
     */
    List<ReservationDTO> findReservationsFromLastWeek();

    List<RoomDTO> findAvailableRooms(HotelDTO hotel, Date dateFrom, Date dateTo);

    List<ReservationDTO> findForUser(UserDTO user);
}
