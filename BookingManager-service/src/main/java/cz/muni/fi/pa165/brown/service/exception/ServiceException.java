package cz.muni.fi.pa165.brown.service.exception;

import org.springframework.dao.DataAccessException;

/**
 * Simple exception wrapping any kind of service failure
 *
 * @author Dominik Labuda
 */
public class ServiceException extends DataAccessException {
    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
