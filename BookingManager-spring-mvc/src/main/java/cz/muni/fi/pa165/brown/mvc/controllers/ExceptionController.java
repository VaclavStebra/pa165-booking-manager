package cz.muni.fi.pa165.brown.mvc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Peter Hutta
 * @version 1.0  13.1.2017
 */
@ControllerAdvice
public class ExceptionController {

    private final static Logger log = LoggerFactory.getLogger(AuthController.class);

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR,
            reason = "Ooops. Sorry, something went wrong and we couldn't perform this operation. "
            + "Take a sip of your coffee, while our admins resolve this error.")
    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex) {
        log.error(ex.getMessage());
    }
}