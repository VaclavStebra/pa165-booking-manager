package cz.muni.fi.pa165.brown.facade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.brown.BeanMappingService;
import cz.muni.fi.pa165.brown.dto.RoomDTO;
import cz.muni.fi.pa165.brown.entity.Hotel;

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
    public RoomDTO update(RoomDTO room) {
        return null;
    }

    @Override
    public RoomDTO findById(Long id) {
        return null;
    }

    @Override
    public List<RoomDTO> findByHotel(Hotel hotel) {
        return null;
    }

    @Override
    public List<RoomDTO> findByCapacity(Integer capacity) {
        return null;
    }

    @Override
    public List<RoomDTO> findAll() {
        return null;
    }
}
