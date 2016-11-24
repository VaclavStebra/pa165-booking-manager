/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.brown.service;

import cz.muni.fi.pa165.brown.entity.Hotel;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * @author Michal Hagara
 */
@Service
public interface HotelService {

    /**
    * create Hotel
    * 
    * @param hotel
    * @return created Hotel
    */
    void create(Hotel hotel) throws DataAccessException;

    /**
     *
     * @param hotel hotel entity to delete
     */
    public void delete(Hotel hotel) throws DataAccessException;;

    /**
     *
     * @param hotel user entity to update
     * @return hotel entity to update
     */
    public Hotel update(Hotel hotel) throws DataAccessException;;
    
    /**
     *
     * @param id id of hotel to find
     * @return hotel entity with given id
     */
    public Hotel findById(Long id) throws DataAccessException;;

    /**
     *
     * @param address address of user to find
     * @return hotel entity with given address
     */
    public Hotel findByAddress(String address) throws DataAccessException;;

    /**
     *
     * @return list of all hotels
     */
    public List<Hotel> findAll() throws DataAccessException;;

    
}

