package cz.muni.fi.pa165.brown.mvc.binders;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.muni.fi.pa165.brown.dto.user.UserDTO;
import cz.muni.fi.pa165.brown.facade.UserFacade;

/**
 * Binds user from string
 *
 * @author Dominik Labuda
 */
@Component
public class UserBinder extends PropertyEditorSupport {

    @Autowired
    private UserFacade userFacade;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        UserDTO userDTO = this.userFacade.findUserById(Long.valueOf(text));

        this.setValue(userDTO);
    }
}
