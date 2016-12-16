package cz.muni.fi.pa165.brown.mvc.binders;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.muni.fi.pa165.brown.dto.room.RoomDTO;
import cz.muni.fi.pa165.brown.facade.RoomFacade;

/**
 * Binds room from string
 *
 * @author Dominik Labuda
 */
@Component
public class RoomBinder extends PropertyEditorSupport {

    @Autowired
    private RoomFacade roomFacade;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        RoomDTO roomDTO = this.roomFacade.findById(Long.valueOf(text));

        this.setValue(roomDTO);
    }
}
