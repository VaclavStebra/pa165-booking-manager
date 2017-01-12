package cz.muni.fi.pa165.brown.controllers;

import cz.muni.fi.pa165.brown.dto.hotel.HotelDTO;
import cz.muni.fi.pa165.brown.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.brown.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.brown.facade.HotelFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * REST Controller for Hotel
 *
 * @author michal hagara
 */
@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Inject
    private HotelFacade hotelFacade;

    /**
     * Get list of Hotels curl -i -X GET http://localhost:8080/pa165/rest/hotel
     *
     * @return HotelDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<HotelDTO> getHotels() {
        return hotelFacade.findAll();
    }

    /**
     *
     * Get hotel by identifier id curl -i -X GET
     * http://localhost:8080/pa165/rest/hotel/{id}
     *
     * @param id identifier for a hotel
     * @return HotelDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HotelDTO getHotel(@PathVariable("id") long id) throws Exception {
        HotelDTO hotelDTO;
        try {
            hotelDTO = hotelFacade.findById(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
        if (hotelDTO != null) {
            return hotelDTO;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Delete one hotel by id curl -i -X DELETE
     * http://localhost:8080/pa165/rest/hotel/{id}
     *
     * @param id identifier for hotel
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteHotel(@PathVariable("id") long id) throws Exception {
        HotelDTO hotelDTO;
        try {
            hotelDTO = hotelFacade.findById(id);
            hotelFacade.delete(hotelDTO);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }


    /**
     * Create a new hotel by POST method curl -X POST -i -H "Content-Type:
     * application/json" --data
     * '{"name":"Hotel","phone":789777654,"address":"adressofhotel"}'
     * http://localhost:8080/pa165/rest/hotel/create
     *
     * @param hotelDTO hotelCreateDTO with required fields for creation
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final void createHotel(@RequestBody HotelDTO hotelDTO) throws Exception {

        try {
            hotelFacade.create(hotelDTO);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }
}