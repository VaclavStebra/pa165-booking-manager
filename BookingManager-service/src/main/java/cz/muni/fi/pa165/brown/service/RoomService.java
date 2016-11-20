package cz.muni.fi.pa165.brown.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.brown.service.exception.ServiceException;
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
     * Update room
     * @param room room entity
     * @return updated room
     * @throws ServiceException when execution fails
     */
    Room update(Room room) throws ServiceException;

    /**
     * Finds room by id
     * @param id room id
     * @return room with given id
     * @throws ServiceException when execution fails
     */
    Room findById(Long id) throws ServiceException;

    /**
     * Finds all rooms belonging to the specific hotel
     * @param hotel hotel
     * @return rooms with given hotel id
     * @throws ServiceException when execution fails
     */
    List<Room> findByHotel(Hotel hotel) throws ServiceException;

    /**
     * Finds room by capacity
     * @param capacity room capacity
     * @return rooms with given capacity
     * @throws ServiceException when execution fails
     */
    List<Room> findByCapacity(Integer capacity) throws ServiceException;

    /**
     * Lists all rooms
     * @return list of all rooms
     * @throws ServiceException when execution fails
     */
    List<Room> findAll() throws ServiceException;

}
