package cz.muni.fi.pa165.brown.service;

import cz.muni.fi.pa165.brown.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Vaclav Stebra
 */
@Service
public interface UserService {

    /**
     * Find user by id
     * @param id user id
     * @return User entity
     */
    User findUserById(Long id) throws DataAccessException;

    /**
     * Find user by email
     * @param email user email
     * @return user entity
     */
    User findUserByEmail(String email) throws DataAccessException;

    /**
     * Returns true if user is admin
     * @param u user entity
     * @return true if user is admin
     */
    boolean isAdmin(User u) throws DataAccessException;

    /**
     * Returns Collection of all users
     * @return
     */
    Collection<User> getAllUsers() throws DataAccessException;

    /**
     * Creates new user
     * @param u user entity
     */
    void createUser(User u) throws DataAccessException;

    /**
     * Returns true if login is successful
     *
     * @param u user
     * @param password password
     * @return whether login was successful
     */
    boolean login(User u, String password);

}
