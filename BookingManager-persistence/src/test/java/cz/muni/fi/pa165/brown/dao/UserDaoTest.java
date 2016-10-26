/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.brown.dao;

import cz.muni.fi.pa165.brown.PersistenceApplicationContext;
import cz.muni.fi.pa165.brown.entity.User;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
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
    private UserDao userDao;

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

