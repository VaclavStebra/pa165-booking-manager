package cz.muni.fi.pa165.brown.facade;


import cz.muni.fi.pa165.brown.ServiceConfig;
import cz.muni.fi.pa165.brown.dto.user.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * @author Vaclav Stebra
 */
@ContextConfiguration(classes = ServiceConfig.class)
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class UserFacadeTest extends AbstractTestNGSpringContextTests {

    private UserDTO u1;
    private UserDTO u2;
    private UserDTO u3;

    @Autowired
    private UserFacade userFacade;

    @Test
    public void findById() {
        UserDTO user = userFacade.findUserById(u1.getId());
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId(), u1.getId());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNullId() {
        userFacade.findUserById(null);
    }

    @Test
    public void findByEmail() {
        UserDTO user = userFacade.findUserByEmail("email1");
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getEmail(), u1.getEmail());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNullEmail() {
        userFacade.findUserByEmail(null);
    }

    @Test
    public void findByWrongEmail() {
        UserDTO user = userFacade.findUserByEmail("email4");
        Assert.assertNull(user);
    }

    @Test
    public void findAll() {
        Assert.assertEquals(userFacade.getAllUsers().size(), 3);
    }

    @Test
    public void isAdmin() {
        Assert.assertTrue(userFacade.isAdmin(u2));
        Assert.assertFalse(userFacade.isAdmin(u3));
    }

    @Test
    public void login() {
        Assert.assertTrue(userFacade.login(userFacade.findUserByEmail("email1"), "pass1"));
        Assert.assertFalse(userFacade.login(userFacade.findUserByEmail("email2"), "pass1"));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullUser() {
        userFacade.createUser(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void isAdminNullUser() {
        userFacade.isAdmin(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void loginNullUser() {
        userFacade.login(null, "asd");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void loginNullPassword() {
        userFacade.login(u1, null);
    }

    @BeforeMethod
    private void createUsers() {
        u1 = new UserDTO();
        u2 = new UserDTO();
        u3 = new UserDTO();

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

        userFacade.createUser(u1);
        userFacade.createUser(u2);
        userFacade.createUser(u3);
    }
}
