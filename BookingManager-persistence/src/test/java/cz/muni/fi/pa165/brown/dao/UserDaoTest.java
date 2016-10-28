package cz.muni.fi.pa165.brown.dao;

import cz.muni.fi.pa165.brown.PersistenceApplicationContext;
import cz.muni.fi.pa165.brown.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
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
 *
 * @author Michal Hagara
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public UserDao userDao;

    @PersistenceContext
    private EntityManager em;

    private User u1;
    private User u2;
    private User u3;

    @BeforeMethod
    public void createUsers() {

        u1 = new User();
        u2 = new User();
        u3 = new User();

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
        u2.setAdmin(false);
        u3.setAdmin(false);

        userDao.create(u1);
        userDao.create(u2);
        userDao.create(u3);
    }

    @Test
    public void findAllTest() {
        List<User> found = userDao.findAll();
        Assert.assertEquals(found.size(), 3);
    }

    @Test
    public void findByEmailTest() {
        User user = userDao.findByEmail("email1");
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getEmail(), "email1");
    }

    @Test
    public void deleteTest() {
        Assert.assertNotNull(userDao.findById(u1.getId()));
        userDao.delete(u1);
        Assert.assertNull(userDao.findById(u1.getId()));
    }

    @Test
    public void updateTest() {
        User user = userDao.findByEmail("email1");
        user.setPassword("verysecurepass");
        userDao.update(user);
        User userChanged = userDao.findByEmail("email1");
        Assert.assertEquals(userChanged.getPassword(), "verysecurepass");
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void TestEmailUniqeness() {
        User user = new User();
        user.setEmail("email1");
        userDao.create(user);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void TestNotNullNameConstraints() {
        User user = new User();
        user.setName(null);
        userDao.create(user);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void TestNotNullSurnameConstraints() {
        User user = new User();
        user.setName(null);
        userDao.create(user);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void TestNotNullAddressConstraints() {
        User user = new User();
        user.setAddress(null);
        userDao.create(user);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void TestNotNullEmailConstraints() {
        User user = new User();
        user.setEmail(null);
        userDao.create(user);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void TestNotNullPasswordConstraints() {
        User user = new User();
        user.setPassword(null);
        userDao.create(user);
    }

}
