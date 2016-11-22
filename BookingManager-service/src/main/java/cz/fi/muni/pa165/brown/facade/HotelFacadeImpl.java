package cz.fi.muni.pa165.brown.facade;

import cz.fi.muni.pa165.brown.BeanMappingService;
import cz.fi.muni.pa165.brown.service.HotelService;
import cz.muni.fi.pa165.brown.dto.HotelDTO;
import cz.muni.fi.pa165.brown.entity.Hotel;
import cz.muni.fi.pa165.brown.facade.HotelFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author michal hagara
 */

@Service
@Transactional
public class HotelFacadeImpl implements HotelFacade{


    @Autowired
    private HotelService hotelService;
    @Autowired
    private BeanMappingService beanMappingService;


    @Override
    public void create(HotelDTO hotelDTO) {
        hotelService.create(beanMappingService.mapTo(hotelDTO, Hotel.class));
    }


    @Override
    public void delete(HotelDTO hotelDTO)
    {
        hotelService.delete(beanMappingService.mapTo(hotelDTO, Hotel.class));
    }

    @Override
    public HotelDTO update(HotelDTO hotelDTO) {
        return beanMappingService.mapTo(hotelService.update(beanMappingService.mapTo(hotelDTO, Hotel.class)), HotelDTO.class);
    }

    @Override
    public HotelDTO findById(Long id) {
        Hotel hotel = hotelService.findById(id);
        if (hotel == null) return null;
        return beanMappingService.mapTo(hotel, HotelDTO.class);
    }



    @Override
    public HotelDTO findByAddress(String address) {
        Hotel hotel = hotelService.findByAddress(address);
        if (hotel == null) return null;
        return beanMappingService.mapTo(hotel, HotelDTO.class);
    }

    @Override
    public List<HotelDTO> findAll() {
        return beanMappingService.mapTo(hotelService.findAll(), HotelDTO.class);
    }

}
