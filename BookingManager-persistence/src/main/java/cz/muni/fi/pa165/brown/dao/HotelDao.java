package cz.muni.fi.pa165.brown.dao;
import cz.muni.fi.pa165.brown.entity.Hotel;
import java.util.List;

/**
 *
 * @author Michal Hagara
 */
public interface HotelDao {

    /**
     *
     * @param hotel hotel entity to create
     */
    public void create(Hotel hotel);

    /**
     *
     * @param hotel hotel entity to delete
     */
    public void delete(Hotel hotel);

    /**
     *
     * @param hotel user entity to update
     * @return hotel entity to update
     */
    public Hotel update(Hotel hotel);
    
    
    
    /**
     *
     * @param id id of hotel to find
     * @return hotel entity with given id
     */
    public Hotel findById(Long id);

    /**
     *
     * @param address address of user to find
     * @return hotel entity with given address
     */
    public Hotel findByAddress(String address);

    /**
     *
     * @return list of all hotel entities
     */
    public List<Hotel> findAll();

    
}
