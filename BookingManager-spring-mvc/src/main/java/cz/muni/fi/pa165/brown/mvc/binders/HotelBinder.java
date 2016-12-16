package cz.muni.fi.pa165.brown.mvc.binders;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.muni.fi.pa165.brown.dto.hotel.HotelDTO;
import cz.muni.fi.pa165.brown.facade.HotelFacade;

/**
 * Binds hotel from String
 *
 * @author Dominik Labuda
 */
@Component
public class HotelBinder extends PropertyEditorSupport {

    @Autowired
    private HotelFacade hotelFacade;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        HotelDTO hotelDTO = this.hotelFacade.findById(Long.valueOf(text));

        this.setValue(hotelDTO);
    }
}
