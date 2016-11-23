package cz.muni.fi.pa165.brown.facade;

import java.util.List;

import cz.muni.fi.pa165.brown.dto.HotelDTO;
import cz.muni.fi.pa165.brown.dto.RoomDTO;

/**
 * Room facade interface for {@link cz.muni.fi.pa165.brown.dto.RoomDTO}
 *
 * @author Dominik Labuda
 */
public interface RoomFacade {

    /**
     * Creates new room
     * @param room room entity
     */
    void create(RoomDTO room);

    /**
     * Delete room
     * @param room room entity
     */
    void delete(RoomDTO room);

    /**
     * Update room
     * @param room room entity
     * @return updated room
     */
    RoomDTO update(RoomDTO room);

    /**
     * Finds room by id
     * @param id room id
     * @return room with given id
     */
    RoomDTO findById(Long id);

    /**
     * Finds all rooms belonging to the specific hotel
     * @param hotel hotel
     * @return rooms with given hotel id
     */
    List<RoomDTO> findByHotel(HotelDTO hotel);

    /**
     * Finds room by capacity
     * @param capacity room capacity
     * @return rooms with given capacity
     */
    List<RoomDTO> findByCapacity(Integer capacity);

    /**
     * Lists all rooms
     * @return list of all rooms
     */
    List<RoomDTO> findAll();

}
