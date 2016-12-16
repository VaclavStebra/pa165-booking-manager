package cz.muni.fi.pa165.brown.mvc.validators;

import cz.muni.fi.pa165.brown.dto.reservation.ReservationCreateDTO;
import cz.muni.fi.pa165.brown.dto.reservation.ReservationDTO;
import cz.muni.fi.pa165.brown.facade.ReservationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * @author Peter Hutta
 */
@Component
public class ReservationCreateValidator implements Validator {

    @Autowired
    private ReservationFacade reservationFacade;

    @Override
    public boolean supports(Class<?> clazz) {
        return ReservationCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ReservationCreateDTO reservationCreateDTO = (ReservationCreateDTO) target;

        if (reservationCreateDTO.getReservedFrom() == null) {
            errors.rejectValue("reservedFrom", "ReservationCreateValidator.field.null");
            return;
        }
        if (reservationCreateDTO.getReservedTo() == null) {
            errors.rejectValue("reservedTo", "ReservationCreateValidator.field.null");
            return;
        }
        if (reservationCreateDTO.getReservedFrom().after(reservationCreateDTO.getReservedTo())) {
            errors.rejectValue("reservedFrom", "ReservationCreateValidator.reservedFrom.order");
            return;
        }

        List<ReservationDTO> reservations = reservationFacade.findReservationsBetweenDates(
                reservationCreateDTO.getReservedFrom(),
                reservationCreateDTO.getReservedTo());

        for(ReservationDTO res : reservations) {
            if (res.getRoom().getId().equals(reservationCreateDTO.getRoomId())) {
                errors.rejectValue("reservedFrom", "ReservationCreateValidator.reservedFrom.occupied");
                return;
            }
        }
    }
}
