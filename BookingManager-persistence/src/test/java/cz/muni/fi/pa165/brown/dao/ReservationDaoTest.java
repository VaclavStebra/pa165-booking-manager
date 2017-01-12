package cz.muni.fi.pa165.brown.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.brown.PersistenceApplicationContext;
import cz.muni.fi.pa165.brown.entity.Hotel;
import cz.muni.fi.pa165.brown.entity.Reservation;
import cz.muni.fi.pa165.brown.entity.Room;
import cz.muni.fi.pa165.brown.entity.User;

/**
 * Tests of {@link cz.muni.fi.pa165.brown.dao.ReservationDao} and its implementation
 *
 * @author Dominik Labuda
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ReservationDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private HotelDao hotelDao;

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createWithNullUser() {
        Reservation reservation = new Reservation();
        reservation.setUser(null);
        reservationDao.create(reservation);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createWithNullRoom() {
        Reservation reservation = new Reservation();
        reservation.setRoom(null);
        reservationDao.create(reservation);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createWithNullFromDate() {
        Reservation reservation = new Reservation();
        reservation.setReservedFrom(null);
        reservationDao.create(reservation);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createWithNullToDate() {
        Reservation reservation = new Reservation();
        reservation.setReservedTo(null);
        reservationDao.create(reservation);
    }

    @Test
    public void update() throws Exception {
        User user1 = createUserInstance("User1Name", "User1Surname", "User1Address", "User1Email", "User1Password", true);
        createUser(user1);

        Hotel hotel1 = createHotelInstance("Hotel1Name", "Hotel1Address", "111111111");
        createHotel(hotel1);

        Room room1 = createRoomInstance(1, new BigDecimal("1.0"), hotel1, "A123");
        createRoom(room1);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        createReservation(user1, room1, sdf.parse("15.10.2016 18:00"), sdf.parse("21.10.2016 10:00"));

        Reservation reservation = reservationDao.findAll().get(0);
        reservation.setReservedTo(sdf.parse("28.11.2016 12:00"));
        reservationDao.update(reservation);

        Reservation changedReservation = reservationDao.findAll().get(0);
        Assert.assertEquals(changedReservation.getReservedTo(), sdf.parse("28.11.2016 12:00"));
    }

    @Test
    public void delete() throws Exception {
        User user1 = createUserInstance("User1Name", "User1Surname", "User1Address", "User1Email", "User1Password", true);
        createUser(user1);

        Hotel hotel1 = createHotelInstance("Hotel1Name", "Hotel1Address", "111111111");
        createHotel(hotel1);

        Room room1 = createRoomInstance(1, new BigDecimal("1.0"), hotel1, "A123");
        createRoom(room1);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Reservation reservation = createReservationInstance(user1, room1, sdf.parse("15.10.2016 18:00"), sdf.parse("21.10.2016 10:00"));

        reservationDao.create(reservation);
        Assert.assertEquals(reservationDao.findAll().size(), 1);
        reservationDao.delete(reservation);
        Assert.assertEquals(reservationDao.findAll().size(), 0);
    }

    @Test
    public void findAll() throws Exception {
        User user1 = createUserInstance("User1Name", "User1Surname", "User1Address", "User1Email", "User1Password", true);
        User user2 = createUserInstance("User2Name", "User2Surname", "User2Address", "User2Email", "User2Password", false);
        createUser(user1);
        createUser(user2);

        Hotel hotel1 = createHotelInstance("Hotel1Name", "Hotel1Address", "111111111");
        Hotel hotel2 = createHotelInstance("Hotel2Name", "Hotel2Address", "222222222");
        createHotel(hotel1);
        createHotel(hotel2);

        Room room1 = createRoomInstance(1, new BigDecimal("1.0"), hotel1, "A123");
        Room room2 = createRoomInstance(2, new BigDecimal("2.0"), hotel2, "B123");
        createRoom(room1);
        createRoom(room2);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        createReservation(user1, room1, sdf.parse("15.10.2016 18:00"), sdf.parse("21.10.2016 10:00"));
        createReservation(user2, room2, sdf.parse("15.11.2016 18:00"), sdf.parse("21.11.2016 10:00"));

        List<Reservation> reservations = reservationDao.findAll();
        Assert.assertEquals(reservations.size(), 2);

        Reservation reservation1 = createReservationInstance(user1, room1, sdf.parse("15.10.2016 18:00"), sdf.parse("21.10.2016 10:00"));
        Reservation reservation2 = createReservationInstance(user2, room2, sdf.parse("15.11.2016 18:00"), sdf.parse("21.11.2016 10:00"));

        Assert.assertTrue(reservations.contains(reservation1));
        Assert.assertTrue(reservations.contains(reservation2));
    }

    // Helper methods

    private void createReservation(User user, Room room, Date reservedFrom, Date reservedTo) {
        reservationDao.create(createReservationInstance(user, room, reservedFrom, reservedTo));
    }

    private Reservation createReservationInstance(User user, Room room, Date reservedFrom, Date reservedTo) {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setReservedFrom(reservedFrom);
        reservation.setReservedTo(reservedTo);
        return reservation;
    }

    private void createUser(String name, String surname, String address, String email, String password, boolean admin) {
        userDao.create(createUserInstance(name, surname,address, email, password, admin));
    }

    private void createUser(User user) {
        userDao.create(user);
    }

    private User createUserInstance(String name, String surname, String address, String email, String password, boolean admin) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setAddress(address);
        user.setEmail(email);
        user.setPassword(password);
        user.setAdmin(admin);
        return user;
    }

    private void createRoom(Integer capacity, BigDecimal pricePerNightPerPerson, Hotel hotel, String roomIdentifier) {
        roomDao.create(createRoomInstance(capacity, pricePerNightPerPerson, hotel, roomIdentifier));
    }

    private void createRoom(Room room) {
        roomDao.create(room);
    }

    private Room createRoomInstance(Integer capacity, BigDecimal pricePerNightPerPerson, Hotel hotel, String roomIdentifier) {
        Room room = new Room();
        room.setCapacity(capacity);
        room.setPricePerNightPerPerson(pricePerNightPerPerson);
        room.setHotel(hotel);
        room.setRoomIdentifier(roomIdentifier);
        return room;
    }

    private void createHotel(String name, String address, String phone) {
        hotelDao.create(createHotelInstance(name, address, phone));
    }

    private void createHotel(Hotel hotel) {
        hotelDao.create(hotel);
    }

    private Hotel createHotelInstance(String name, String address, String phone) {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setAddress(address);
        hotel.setPhone(phone);
        return hotel;
    }

}
