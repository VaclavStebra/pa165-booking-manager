package cz.muni.fi.pa165.brown.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cz.muni.fi.pa165.brown.dao.RoomDao;
import cz.muni.fi.pa165.brown.entity.Hotel;
import cz.muni.fi.pa165.brown.entity.Room;
import cz.muni.fi.pa165.brown.service.RoomService;
import cz.muni.fi.pa165.brown.service.exception.ServiceException;

/**
 * Implementation of {@link cz.muni.fi.pa165.brown.service.RoomService}
 *
 * @author Dominik Labuda
 */
public class RoomServiceImpl implements RoomService {

    /** Logger */
    private final static Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

    @Autowired
    private RoomDao roomDao;

    @Override
    public Room update(Room room) throws ServiceException {
        try {
            return roomDao.update(room);
        } catch (Throwable t) {
            String message = "Cannot update room with id=" + room.getId();
            logger.error(message, t);
            throw new ServiceException(message, t);
        }
    }

    @Override
    public Room findById(Long id) throws ServiceException {
        try {
            return roomDao.findById(id);
        } catch (Throwable t) {
            String message = "Could not get room with id=" + id;
            logger.error(message, t);
            throw new ServiceException(message, t);
        }
    }

    @Override
    public List<Room> findByHotel(Hotel hotel) throws ServiceException {
        try {
            return roomDao.findByHotel(hotel);
        } catch (Throwable t) {
            String message = "Could not get rooms with hotel=" + hotel;
            logger.error(message, t);
            throw new ServiceException(message, t);
        }
    }

    @Override
    public List<Room> findByCapacity(Integer capacity) throws ServiceException {
        try {
            return roomDao.findByCapacity(capacity);
        } catch (Throwable t) {
            String message = "Could not get all rooms with capacity=" + capacity;
            logger.error(message, t);
            throw new ServiceException(message, t);
        }
    }

    @Override
    public List<Room> findAll() throws ServiceException {
        try {
            return roomDao.findAll();
        } catch (Throwable t) {
            String message = "Could not get all rooms";
            logger.error(message, t);
            throw new ServiceException(message, t);
        }
    }
}
