package cz.muni.fi.pa165.brown.dao;

import cz.muni.fi.pa165.brown.PersistenceApplicationContext;
import cz.muni.fi.pa165.brown.entity.Hotel;
import cz.muni.fi.pa165.brown.entity.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Peter Hutta
 * @version 1.0  29.10.2016
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RoomDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private HotelDao hotelDao;

    @Test
    public void updateTest() {
        Hotel h1 = buildHotel("hotel1", "address1", "phone1");
        hotelDao.create(h1);

        Room r1 = buildRoom(10, new BigDecimal("1.0"), h1, "A101");
        roomDao.create(r1);

        r1.setRoomIdentifier("SomeIdentifier");
        roomDao.update(r1);

        Room updatedRoom = roomDao.findById(r1.getId());
        Assert.assertEquals(updatedRoom.getRoomIdentifier(), r1.getRoomIdentifier());
    }

    @Test
    public void deleteTest() {
        Hotel h1 = buildHotel("hotel1", "address1", "phone1");
        hotelDao.create(h1);

        Room r1 = buildRoom(10, new BigDecimal("1.0"), h1, "A101");
        Room r2 = buildRoom(1, new BigDecimal("10.0"), h1, "B102");
        roomDao.create(r1);
        roomDao.create(r2);

        Assert.assertEquals(roomDao.findAll().size(), 2);
        Assert.assertNotNull(roomDao.findById(r1.getId()));

        roomDao.delete(r1);

        Assert.assertEquals(roomDao.findAll().size(), 1);
        Assert.assertNull(roomDao.findById(r1.getId()));
    }

    @Test
    public void findByIdTest() {
        Hotel h1 = buildHotel("hotel1", "address1", "phone1");
        hotelDao.create(h1);

        Room r1 = buildRoom(10, new BigDecimal("1.0"), h1, "A101");
        roomDao.create(r1);

        Room found = roomDao.findById(r1.getId());
        Assert.assertEquals(found, r1);
    }

    @Test
    public void findAllTest() {
        Hotel h1 = buildHotel("hotel1", "address1", "phone1");
        hotelDao.create(h1);

        Room r1 = buildRoom(10, new BigDecimal("1.0"), h1, "A101");
        Room r2 = buildRoom(5, new BigDecimal("2.0"), h1, "A102");
        Room r3 = buildRoom(1, new BigDecimal("10.0"), h1, "B120");
        roomDao.create(r1);
        roomDao.create(r2);
        roomDao.create(r3);

        List<Room> rooms = roomDao.findAll();
        Assert.assertEquals(rooms.size(), 3);

        Assert.assertTrue(rooms.contains(r1));
        Assert.assertTrue(rooms.contains(r2));
        Assert.assertTrue(rooms.contains(r3));
    }

    @Test
    public void findByHotelTest() {
        Hotel h1 = buildHotel("hotel1", "address1", "phone1");
        Hotel h2 = buildHotel("hotel2", "address2", "phone2");
        Hotel h3 = buildHotel("hotel3", "address3", "phone3");
        hotelDao.create(h1);
        hotelDao.create(h2);
        hotelDao.create(h3);

        Room r1 = buildRoom(10, new BigDecimal("1.0"), h1, "A101");
        Room r2 = buildRoom(1, new BigDecimal("10.0"), h2, "B120");
        roomDao.create(r1);
        roomDao.create(r2);

        List<Room> roomsH1 = roomDao.findByHotel(h1);
        Assert.assertEquals(roomsH1.size(), 1);
        Assert.assertEquals(roomsH1.get(0), r1);

        List<Room> roomsH2 = roomDao.findByHotel(h2);
        Assert.assertEquals(roomsH2.size(), 1);
        Assert.assertEquals(roomsH2.get(0), r2);

        List<Room> roomsH3 = roomDao.findByHotel(h3);
        Assert.assertEquals(roomsH3.size(), 0);
    }

    @Test
    public void findByCapacityTest() {
        Hotel h1 = buildHotel("hotel1", "address1", "phone1");
        hotelDao.create(h1);

        Room r1 = buildRoom(10, new BigDecimal("1.0"), h1, "A101");
        Room r2 = buildRoom(1, new BigDecimal("10.0"), h1, "B102");
        roomDao.create(r1);
        roomDao.create(r2);

        List<Room> rooms1 = roomDao.findByCapacity(10);
        Assert.assertEquals(rooms1.size(), 1);
        Assert.assertEquals(rooms1.get(0), r1);

        List<Room> rooms2 = roomDao.findByCapacity(42);
        Assert.assertEquals(rooms2.size(), 0);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createWithNullCapacityTest() {
        Room room = new Room();
        room.setCapacity(null);
        roomDao.create(room);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createWithNullPricePerNightPerPersonTest() {
        Room room = new Room();
        room.setPricePerNightPerPerson(null);
        roomDao.create(room);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createWithNullHotelTest() {
        Room room = new Room();
        room.setHotel(null);
        roomDao.create(room);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createWithNullRoomIdentifierTest() {
        Room room = new Room();
        room.setRoomIdentifier(null);
        roomDao.create(room);
    }

    private Hotel buildHotel(String name, String address, String phone) {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setPhone(phone);
        hotel.setAddress(address);
        return hotel;
    }

    private Room buildRoom(Integer capacity, BigDecimal pricePerNightPerPerson, Hotel hotel, String roomIdentifier) {
        Room room = new Room();
        room.setCapacity(capacity);
        room.setPricePerNightPerPerson(pricePerNightPerPerson);
        room.setRoomIdentifier(roomIdentifier);
        room.setHotel(hotel);
        return room;
    }
}
