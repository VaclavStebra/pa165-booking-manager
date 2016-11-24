/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.brown.service.impl;

import cz.muni.fi.pa165.brown.dao.HotelDao;
import cz.muni.fi.pa165.brown.entity.Hotel;
import cz.muni.fi.pa165.brown.service.HotelService;
import cz.muni.fi.pa165.brown.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * 
 *
 * @author michal hagara
 */
@Service
public class HotelServiceImpl implements HotelService {
    private final static Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

    @Autowired
    private HotelDao hotelDao;
    
    @Override        
    public void create(Hotel hotel) throws ServiceException {
            try {
                hotelDao.create(hotel);
            } catch (Throwable t) {
                String message = "Could not create hotel: " + hotel;
                logger.error(message, t);
                throw new ServiceException(message, t);
            }
        }




    @Override
    public void delete(Hotel hotel) throws ServiceException {
        try {
            hotelDao.delete(hotel);
        } catch (Throwable t) {
            String message = "Could not delete hotel: " + hotel;
            logger.error(message, t);
            throw new ServiceException(message, t);
        }
    }

    @Override
    public Hotel update(Hotel hotel) throws ServiceException {
        try {
            hotelDao.update(hotel);
            return hotel;
        } catch (Throwable t) {
            String message = "Could not update hotel: " + hotel;
            logger.error(message, t);
            throw new ServiceException(message, t);
        }
    }


    @Override
    public Hotel findById(Long id) throws ServiceException {
            try {
                return hotelDao.findById(id);
            } catch (Throwable t) {
                String message = "Could not get hotel with id=" + id;
                logger.error(message, t);
                throw new ServiceException(message, t);
            }



    }

    @Override
    public Hotel findByAddress(String address) throws ServiceException {
        try {
            return hotelDao.findByAddress(address);
        } catch (Throwable t) {
            String message = "Could not get hotel with given address" + address;
            logger.error(message, t);
            throw new ServiceException(message, t);
        }

    }

    @Override
    public List<Hotel> findAll() throws ServiceException {
        try {
            return hotelDao.findAll();
        } catch (Throwable t) {
            String message = "Could not get all hotels";
            logger.error(message, t);
            throw new ServiceException(message, t);
        }
    }
}
