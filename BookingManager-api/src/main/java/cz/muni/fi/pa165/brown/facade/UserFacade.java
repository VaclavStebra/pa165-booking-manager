package cz.muni.fi.pa165.brown.facade;

import cz.muni.fi.pa165.brown.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Vaclav Stebra
 */
@Service
public interface UserFacade {

    /**
     *
     * @param id user id
     * @return User dto
     */
    UserDTO findUserById(Long id);

    /**
     *
     * @param email user email
     * @return user dto
     */
    UserDTO findUserByEmail(String email);

    /**
     *
     * @return collection of user dto for all users
     */
    Collection<UserDTO> getAllUsers();

    /**
     * Creates new user in the system
     * @param user user dto
     */
    void createUser(UserDTO user);

    /**
     *
     * @param u user dto
     * @return true if user is admin
     */
    boolean isAdmin(UserDTO u);

    /**
     *
     * @param u user dto
     * @param password password
     * @return true if password is correct
     */
    boolean login(UserDTO u, String password);

}
