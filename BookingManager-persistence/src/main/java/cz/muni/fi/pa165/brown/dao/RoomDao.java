package cz.muni.fi.pa165.brown.dao;

import java.util.List;

import cz.muni.fi.pa165.brown.entity.Room;

/**
 * Room entity DAO
 *
 * @author Dominik Labuda
 */
public interface RoomDao {

    /**
     * Creates new room
     * @param room room entity
     */
    void create(Room room);

    /**
     * Delete room
     * @param room room entity
     */
    void delete(Room room);

    /**
     * Update room
     * @param room room entity
     * @return updated room
     */
    Room update(Room room);

    /**
     * Finds room by id
     * @param id room id
     * @return room with given id
     */
    Room findById(Long id);

    /**
     * Finds all rooms belonging to the specific hotel
     * @param hotelId ID of the hotel
     * @return rooms with given hotel id
     */
    List<Room> findByHotelId(Long hotelId);

    /**
     * Finds room by capacity
     * @param capacity room capacity
     * @return rooms with given capacity
     */
    List<Room> findByCapacity(Integer capacity);

    /**
     * Finds the rooms by occupation
     * @param free true if free rooms are to be found, false if occupied
     * @return list of free or occupied rooms
     */
    List<Room> findByOccupation(Boolean free);

    /**
     * Lists all rooms
     * @return list of all rooms
     */
    List<Room> findAll();

}
