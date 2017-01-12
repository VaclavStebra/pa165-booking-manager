package cz.muni.fi.pa165.brown.facade;

import cz.muni.fi.pa165.brown.BeanMappingService;
import cz.muni.fi.pa165.brown.service.UserService;
import cz.muni.fi.pa165.brown.dto.user.UserDTO;
import cz.muni.fi.pa165.brown.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author Vaclav Stebra
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public UserDTO findUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        User user = userService.findUserById(id);
        if (user == null) {
            return null;
        }
        return beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("email is null");
        }
        User user = userService.findUserByEmail(email);
        if (user == null) {
            return null;
        }
        return beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public Collection<UserDTO> getAllUsers() {
        return beanMappingService.mapTo(userService.getAllUsers(), UserDTO.class);
    }

    @Override
    public void createUser(UserDTO user) {
        if (user == null) {
            throw new IllegalArgumentException("user dto is null");
        }
        User userEntity = beanMappingService.mapTo(user, User.class);
        userService.createUser(userEntity);
        user.setId(userEntity.getId());
    }

    @Override
    public boolean isAdmin(UserDTO u) {
        if (u == null) {
            throw new IllegalArgumentException("user dto is null");
        }
        return userService.isAdmin(beanMappingService.mapTo(u, User.class));
    }

    @Override
    public boolean login(UserDTO u, String password) {
        if (u == null) {
            throw new IllegalArgumentException("user dto is null");
        }
        if (password == null) {
            throw new IllegalArgumentException("password is null");
        }
        return userService.login(beanMappingService.mapTo(u, User.class), password);
    }
}
