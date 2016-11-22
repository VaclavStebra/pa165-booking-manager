/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.brown.service;

import cz.muni.fi.pa165.brown.dao.HotelDao;
import cz.muni.fi.pa165.brown.entity.Hotel;
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
public class HotelServiceImpl implements HotelService 
{
    @Autowired
    private HotelDao hotelDao;
    
    @Override        
    public Hotel create(Hotel hotel){

        if(hotel == null) throw new IllegalArgumentException("param hotel is null");

        hotelDao.create(hotel);

        return hotel;
    }


    @Override
    public void delete(Hotel hotel) {
        if(hotel == null) throw new IllegalArgumentException("param hotel is null");
        
        hotelDao.update(hotel);
        
    }

    @Override
    public Hotel update(Hotel hotel) {
        if(hotel == null) throw new IllegalArgumentException("param trip is null");
        
        hotelDao.update(hotel);
        
        return hotel;
    }

    @Override
    public Hotel findById(Long id) {
        if (id == null || id < 0) return null;
        
        return hotelDao.findById(id);
    }

    @Override
    public Hotel findByAddress(String address) {
        return hotelDao.findByAddress(address);
    }

    @Override
    public List<Hotel> findAll() {
        return hotelDao.findAll();
    }
    
}
