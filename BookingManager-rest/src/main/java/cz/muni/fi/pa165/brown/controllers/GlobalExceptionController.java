package cz.muni.fi.pa165.brown.controllers;

import cz.muni.fi.pa165.brown.ApiError;
import cz.muni.fi.pa165.brown.exceptions.ResourceAlreadyExistingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

/**
 * @author michal hagara
 */
@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    ApiError handleException(ResourceAlreadyExistingException ex) {
        ApiError apiError = new ApiError();
        apiError.setErrors(Arrays.asList("the requested resource already exists"));
        return apiError;
    }
}