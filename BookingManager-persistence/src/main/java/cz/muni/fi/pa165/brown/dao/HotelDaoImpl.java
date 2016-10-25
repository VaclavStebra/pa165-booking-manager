package cz.muni.fi.pa165.brown.dao;

import cz.muni.fi.pa165.brown.entity.Hotel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Michal Hagara
 */
@Repository
@Transactional
public class HotelDaoImpl implements HotelDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Hotel findById(Long id) {
        return em.find(Hotel.class, id);
    }

    @Override
    public Hotel findByAddress(String address) {
        List<Hotel> hts = em.createQuery("SELECT ht FROM Hotel ht WHERE ht.address = :address", Hotel.class)
                .setParameter("address", address).getResultList();
        if (hts.isEmpty()) {
            return null;
        }
        return hts.get(0);
    }

    @Override
    public List<Hotel> findAll() {
        return em.createQuery("select ht from Hotel ht", Hotel.class).getResultList();
    }

    @Override
    public void create(Hotel ht) {
        em.persist(ht);
    }

    @Override
    public void delete(Hotel ht) {
        em.remove(ht);
    }

    @Override
    public Hotel update(Hotel ht) {
        return em.merge(ht);
    }
}
