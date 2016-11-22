package cz.muni.fi.pa165.brown.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.brown.entity.Hotel;
import cz.muni.fi.pa165.brown.entity.Room;

/**
 * Room service
 *
 * @author Dominik Labuda
 */
@Service
public interface RoomService {

    /**
     * Creates new room
     * @param room room entity
     * @throws DataAccessException when execution fails
     */
    void create(Room room) throws DataAccessException;

    /**
     * Delete room
     * @param room room entity
     * @throws DataAccessException when execution fails
     */
    void delete(Room room) throws DataAccessException;

    /**
     * Update room
     * @param room room entity
     * @return updated room
     * @throws DataAccessException when execution fails
     */
    Room update(Room room) throws DataAccessException;

    /**
     * Finds room by id
     * @param id room id
     * @return room with given id
     * @throws DataAccessException when execution fails
     */
    Room findById(Long id) throws DataAccessException;

    /**
     * Finds all rooms belonging to the specific hotel
     * @param hotel hotel
     * @return rooms with given hotel id
     * @throws DataAccessException when execution fails
     */
    List<Room> findByHotel(Hotel hotel) throws DataAccessException;

    /**
     * Finds room by capacity
     * @param capacity room capacity
     * @return rooms with given capacity
     * @throws DataAccessException when execution fails
     */
    List<Room> findByCapacity(Integer capacity) throws DataAccessException;

    /**
     * Lists all rooms
     * @return list of all rooms
     * @throws DataAccessException when execution fails
     */
    List<Room> findAll() throws DataAccessException;

}
