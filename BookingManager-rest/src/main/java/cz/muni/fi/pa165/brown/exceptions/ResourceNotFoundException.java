package cz.muni.fi.pa165.brown.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by michal hagara
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="The requested resource was not found")
public class ResourceNotFoundException extends RuntimeException {

}
