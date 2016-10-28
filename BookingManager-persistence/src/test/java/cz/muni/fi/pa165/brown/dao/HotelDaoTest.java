package cz.muni.fi.pa165.brown.dao;

import cz.muni.fi.pa165.brown.PersistenceApplicationContext;
import cz.muni.fi.pa165.brown.entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolationException;

import java.util.List;


/**
 * @author VaclavStebra
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class HotelDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private HotelDao hotelDao;

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createWithNullName() {
        Hotel hotel = new Hotel();
        hotel.setName(null);
        hotelDao.create(hotel);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createWithNullAddress() {
        Hotel hotel = new Hotel();
        hotel.setAddress(null);
        hotelDao.create(hotel);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createWithNullPhone() {
        Hotel hotel = new Hotel();
        hotel.setPhone(null);
        hotelDao.create(hotel);
    }

    @Test
    public void findByAddress() {
        createHotel("Test", "Test address", "123456789");
        Hotel hotel = hotelDao.findByAddress("Test address");
        Assert.assertNotNull(hotel);
        Assert.assertEquals(hotel.getName(), "Test");
    }

    @Test
    public void findByWrongAddress() {
        createHotel("Test", "Test address", "123456789");
        Assert.assertNull(hotelDao.findByAddress("Wrong"));
    }

    @Test
    public void findAll() {
        createHotel("Test", "Test address 1", "123456789");
        createHotel("Test 2", "Test address 2", "987654321");
        List<Hotel> hotels = hotelDao.findAll();
        Assert.assertEquals(hotels.size(), 2);
        Hotel hotel1 = buildHotel("Test", "Test address 1", "123456789");
        Hotel hotel2 = buildHotel("Test 2", "Test address 2", "987654321");
        Assert.assertTrue(hotels.contains(hotel1));
        Assert.assertTrue(hotels.contains(hotel2));
    }

    @Test
    public void update() {
        createHotel("Test", "Test address", "123456");
        Hotel hotel = hotelDao.findByAddress("Test address");
        hotel.setName("Changed");
        hotelDao.update(hotel);
        Hotel hotel2 = hotelDao.findByAddress("Test address");
        Assert.assertEquals(hotel2.getName(), "Changed");
    }

    @Test
    public void delete() {
        Hotel hotel = buildHotel("Test", "Test address 1", "123456789");
        hotelDao.create(hotel);
        Assert.assertEquals(hotelDao.findAll().size(), 1);
        hotelDao.delete(hotel);
        Assert.assertEquals(hotelDao.findAll().size(), 0);
    }

    private void createHotel(String name, String address, String phone) {
        Hotel hotel = buildHotel(name, address, phone);
        hotelDao.create(hotel);
    }

    private Hotel buildHotel(String name, String address, String phone) {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setAddress(address);
        hotel.setPhone(phone);
        return hotel;
    }
}