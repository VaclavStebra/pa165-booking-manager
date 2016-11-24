package cz.muni.fi.pa165.brown;

import cz.muni.fi.pa165.brown.dao.UserDao;
import cz.muni.fi.pa165.brown.entity.User;
import cz.muni.fi.pa165.brown.service.UserService;
import cz.muni.fi.pa165.brown.service.exception.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Vaclav Stebra
 */
@ContextConfiguration(classes = ServiceConfig.class)
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private UserDao userDao;

    @InjectMocks
    @Autowired
    private UserService userService;

    private User u1;
    private User u2;
    private User u3;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findUserById() {
        User user = userService.findUserById(u1.getId());
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getEmail(), u1.getEmail());
        Assert.assertNull(userService.findUserById(4l));
    }

    @Test
    public void findUserByEmail() {
        User user = userService.findUserByEmail(u1.getEmail());
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId(), u1.getId());
        Assert.assertNull(userService.findUserByEmail("wrong"));
    }

    @Test
    public void isAdmin() {
        User user1 = userService.findUserById(u1.getId());
        Assert.assertEquals(user1.isAdmin(), u1.isAdmin());
        User user2 = userService.findUserById(u2.getId());
        Assert.assertEquals(user2.isAdmin(), u2.isAdmin());
    }

    @Test
    public void getAllUsers() {
        Collection<User> users = userService.getAllUsers();
        Assert.assertEquals(users.size(), 3);
    }

    @Test
    public void loginSuccess() {
        Assert.assertTrue(userService.login(u1, "pass1"));
    }

    @Test
    public void loginFailure() {
        Assert.assertFalse(userService.login(u1, "pass"));
    }

    @Test
    public void create() {
        User u = new User();
        u.setName("user");
        u.setSurname("surname");
        u.setEmail("email");
        u.setAddress("address");
        u.setPassword("pass");
        u.setAdmin(false);

        userService.createUser(u);
        verify(userDao).create(u);
    }

    @BeforeMethod
    private void createUsers() {
        u1 = new User();
        u2 = new User();
        u3 = new User();

        u1.setId(1L);
        u2.setId(2L);
        u3.setId(3L);

        u1.setName("user1");
        u2.setName("user2");
        u3.setName("user3");

        u1.setSurname("surname1");
        u2.setSurname("surname2");
        u3.setSurname("surname3");

        u1.setEmail("email1");
        u2.setEmail("email2");
        u3.setEmail("email3");

        u1.setAddress("address1");
        u2.setAddress("address2");
        u3.setAddress("address3");

        u1.setPassword("pass1");
        u2.setPassword("pass2");
        u3.setPassword("pass3");

        u1.setAdmin(false);
        u2.setAdmin(true);
        u3.setAdmin(false);

        userService.createUser(u1);
        userService.createUser(u2);
        userService.createUser(u3);

        when(userDao.findById(u1.getId())).thenReturn(u1);
        when(userDao.findById(u2.getId())).thenReturn(u2);
        when(userDao.findById(u3.getId())).thenReturn(u3);
        when(userDao.findById(4l)).thenReturn(null);

        when(userDao.findByEmail(u1.getEmail())).thenReturn(u1);
        when(userDao.findByEmail(u2.getEmail())).thenReturn(u2);
        when(userDao.findByEmail(u3.getEmail())).thenReturn(u3);
        when(userDao.findByEmail("wrong")).thenReturn(null);

        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        users.add(u3);
        when(userDao.findAll()).thenReturn(users);
    }

}
