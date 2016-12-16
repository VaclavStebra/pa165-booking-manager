package cz.muni.fi.pa165.brown.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.brown.entity.Hotel;
import cz.muni.fi.pa165.brown.entity.Room;

/**
 * Implementation of {@link cz.muni.fi.pa165.brown.dao.RoomDao}
 *
 * @author Dominik Labuda
 */
@Repository
@Transactional
public class RoomDaoImpl implements RoomDao {

    /** Entity manager */
    @PersistenceContext
    private EntityManager em;

    @Override
    public Room create(Room room) {
        em.persist(room);
        return room;
    }

    @Override
    public Room delete(Room room) {
        em.remove(em.contains(room) ? room : em.merge(room));
        return room;
    }

    @Override
    public Room update(Room room) {
        return em.merge(room);
    }

    @Override
    public Room findById(Long id) {
        return em.find(Room.class, id);
    }

    @Override
    public List<Room> findByHotel(Hotel hotel) {
        return em.createQuery("select room from Room room where room.hotel.id = :hotelId", Room.class)
                .setParameter("hotelId", hotel.getId()).getResultList();
    }

    @Override
    public List<Room> findByCapacity(Integer capacity) {
        return em.createQuery("select room from Room room where room.capacity = :capacity", Room.class)
                .setParameter("capacity", capacity).getResultList();
    }

    @Override
    public List<Room> findAll() {
        return em.createQuery("select room from Room room", Room.class).getResultList();
    }
}
