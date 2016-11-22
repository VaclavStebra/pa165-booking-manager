/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.brown.service;

import cz.muni.fi.pa165.brown.entity.Hotel;

import java.util.List;


/**
 *
 * @author Michal Hagara
 */
public interface HotelService {

    /**
    * create Hotel
    * 
    * @param hotel
    * @return created Hotel
    */ 
    Hotel create(Hotel hotel);

    /**
     *
     * @param hotel hotel entity to delete
     */
    public void delete(Hotel hotel);

    /**
     *
     * @param hotel user entity to update
     * @return hotel entity to update
     */
    public Hotel update(Hotel hotel);
    
    /**
     *
     * @param id id of hotel to find
     * @return hotel entity with given id
     */
    public Hotel findById(Long id);

    /**
     *
     * @param address address of user to find
     * @return hotel entity with given address
     */
    public Hotel findByAddress(String address);

    /**
     *
     * @return list of all hotels
     */
    public List<Hotel> findAll();

    
}

