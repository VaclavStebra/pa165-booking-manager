package cz.muni.fi.pa165.brown.facade;

import cz.fi.muni.pa165.brown.BeanMappingService;
import cz.muni.fi.pa165.brown.service.ReservationService;
import cz.muni.fi.pa165.brown.dto.ReservationDTO;
import cz.muni.fi.pa165.brown.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @author Peter Hutta
 */
@Service
@Transactional
public class ReservationFacadeImpl implements ReservationFacade {

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private ReservationService reservationService;

    @Override
    public Long create(ReservationDTO reservation) {
        return reservationService.create(beanMappingService.mapTo(reservation, Reservation.class));
    }

    @Override
    public void update(ReservationDTO reservation) {
        reservationService.update(beanMappingService.mapTo(reservation, Reservation.class));
    }

    @Override
    public void delete(ReservationDTO reservation) {
        reservationService.delete(beanMappingService.mapTo(reservation, Reservation.class));
    }

    @Override
    public List<ReservationDTO> findAll() {
        return beanMappingService.mapTo(reservationService.findAll(), ReservationDTO.class);
    }

    @Override
    public ReservationDTO findById(Long id) {
        Reservation reservation = reservationService.findById(id);
        return (reservation == null) ? null : beanMappingService.mapTo(reservation, ReservationDTO.class);
    }

    @Override
    public List<ReservationDTO> findReservationsBetweenDates(Date dateFrom, Date dateTo) {
        return beanMappingService.mapTo(
                reservationService.findReservationsBetweenDates(dateFrom, dateTo), ReservationDTO.class);
    }

    @Override
    public List<ReservationDTO> findReservationsFromLastWeek() {
        return beanMappingService.mapTo(
                reservationService.findReservationsFromLastNDays(7), ReservationDTO.class);
    }
}
