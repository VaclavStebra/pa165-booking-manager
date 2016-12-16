package cz.muni.fi.pa165.brown.facade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.brown.BeanMappingService;
import cz.muni.fi.pa165.brown.dto.hotel.HotelDTO;
import cz.muni.fi.pa165.brown.dto.room.RoomDTO;
import cz.muni.fi.pa165.brown.entity.Hotel;
import cz.muni.fi.pa165.brown.entity.Room;
import cz.muni.fi.pa165.brown.service.RoomService;
import cz.muni.fi.pa165.brown.service.exception.ServiceException;

/**
 * Implementation of RoomFacade interface
 *
 * @author Dominik Labuda
 */
@Service
@Transactional
public class RoomFacadeImpl implements RoomFacade {

    /** Logger */
    private final static Logger logger = LoggerFactory.getLogger(RoomFacadeImpl.class);

    /** Room service */
    @Autowired
    private RoomService roomService;

    /** Mapping service */
    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public void create(RoomDTO room) {
        try {
            Room roomEntity = beanMappingService.mapTo(room, Room.class);
            roomService.create(roomEntity);
            room.setId(roomEntity.getId());
        } catch (ServiceException e) {
            logger.warn(e.getMessage(), e);
        }
    }

    @Override
    public void delete(RoomDTO room) {
        try {
            roomService.delete(beanMappingService.mapTo(room, Room.class));
        } catch (ServiceException e) {
            logger.warn(e.getMessage(), e);
        }
    }

    @Override
    public RoomDTO update(RoomDTO room) {
        Room roomObject = beanMappingService.mapTo(room, Room.class);
        try {
            roomService.update(roomObject);
            room.setId(roomObject.getId());
            return room;
        } catch (ServiceException e) {
            logger.warn(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public RoomDTO findById(Long id) {
        try {
            return beanMappingService.mapTo(roomService.findById(id), RoomDTO.class);
        } catch (ServiceException e) {
            logger.warn(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<RoomDTO> findByHotel(HotelDTO hotel) {
        try {
            return beanMappingService.mapTo(
                    roomService.findByHotel(beanMappingService.mapTo(hotel, Hotel.class)),
                    RoomDTO.class);
        } catch (ServiceException e) {
            logger.warn(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<RoomDTO> findByCapacity(Integer capacity) {
        try {
            return beanMappingService.mapTo(roomService.findByCapacity(capacity), RoomDTO.class);
        } catch (ServiceException e) {
            logger.warn(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<RoomDTO> findAll() {
        try {
            return beanMappingService.mapTo(roomService.findAll(), RoomDTO.class);
        } catch (ServiceException e) {
            logger.warn(e.getMessage(), e);
        }
        return null;
    }
}
