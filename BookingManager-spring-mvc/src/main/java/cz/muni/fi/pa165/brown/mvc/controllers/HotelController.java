package cz.muni.fi.pa165.brown.mvc.controllers;


import cz.muni.fi.pa165.brown.dto.HotelDTO;
import cz.muni.fi.pa165.brown.dto.RoomDTO;
import cz.muni.fi.pa165.brown.facade.HotelFacade;
import cz.muni.fi.pa165.brown.facade.RoomFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vaclav Stebra
 */

@Controller
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelFacade hotelFacade;

    @Autowired
    private RoomFacade roomFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<HotelDTO> hotels = hotelFacade.findAll();
        model.addAttribute("hotels", hotels);

        Map<Long, List<RoomDTO>> roomsForHotel = new HashMap<>();
        for (HotelDTO hotel : hotels) {
            roomsForHotel.put(hotel.getId(), roomFacade.findByHotel(hotel));
        }
        model.addAttribute("roomsForHotel", roomsForHotel);
        return "hotel/list";
    }
}
