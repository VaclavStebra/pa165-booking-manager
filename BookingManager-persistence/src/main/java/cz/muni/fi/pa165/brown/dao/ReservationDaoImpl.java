package cz.muni.fi.pa165.brown.dao;

import cz.muni.fi.pa165.brown.entity.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * @author Peter Hutta
 * @version 2.0  22.11.2016
 */
@Repository
@Transactional
public class ReservationDaoImpl implements ReservationDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Reservation reservation) {
        em.persist(reservation);
    }

    @Override
    public void update(Reservation reservation) {
        em.merge(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        em.remove(reservation);
    }

    @Override
    public Reservation findById(Long id) {
        return em.find(Reservation.class, id);
    }

    @Override
    public List<Reservation> findAll() {
        return em.createQuery("SELECT res FROM Reservation res", Reservation.class).getResultList();
    }

    @Override
    public List<Reservation> findReservationsBetweenDates(Date dateFrom, Date dateTo) {
        TypedQuery<Reservation> query = em.createQuery(
                "SELECT res FROM Reservation res WHERE (res.reservedFrom BETWEEN :dateFrom AND :dateTo) OR " +
                        "(res.reservedTo BETWEEN :dateFrom AND :dateTo)", Reservation.class);
        query.setParameter("dateFrom", dateFrom);
        query.setParameter("dateTo", dateTo);
        return query.getResultList();
    }
}
