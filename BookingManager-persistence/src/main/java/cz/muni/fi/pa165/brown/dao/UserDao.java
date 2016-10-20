package cz.muni.fi.pa165.brown.dao;

import cz.muni.fi.pa165.brown.entity.User;

import java.util.List;

/**
 *
 * @author Vaclav Stebra
 */
public interface UserDao {

    /**
     *
     * @param id id of user to find
     * @return user entity with given id
     */
    public User findById(Long id);

    /**
     *
     * @param email email of user to find
     * @return user entity with given email
     */
    public User findByEmail(String email);

    /**
     *
     * @return list of all user entities
     */
    public List<User> findAll();

    /**
     *
     * @param u user entity to create
     */
    public void create(User u);

    /**
     *
     * @param u user entity to delete
     */
    public void delete(User u);

    /**
     *
     * @param u user entity to update
     */
    public User update(User u);
}
