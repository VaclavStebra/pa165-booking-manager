/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.brown.facade;

import cz.muni.fi.pa165.brown.dto.hotel.HotelDTO;

import java.util.List;

/**
 *
 * @author michal hagara
 */
public interface HotelFacade
{
    /*
     * Creates a hotel
     *
     * @param hotel to be created
     */
    public void create(HotelDTO hotel);

    /*
      * Deletes a hotel
      *
      * @param hotel to be deleted
      */
    public void delete(HotelDTO hotel);


    /*
     * Updates a hotel
     *
     * @param hotel to be updated
     */
    public HotelDTO update(HotelDTO hotel);


    /**
     * Finds Hotel by id
     *
     * @param id - id of a Hotel to find
     * @return Hotel
     */
    public HotelDTO findById(Long id);

    /**
     * Finds Hotel by id
     *
     * @param address - address of a Hotel to find
     * @return Hotel
     */
    public HotelDTO findByAddress(String address);

    /**
     * Finds all hotels in the DB
     *
     * @return Collection of hotels found
     */
    public List<HotelDTO> findAll();


}
