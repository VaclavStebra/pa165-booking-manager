package cz.muni.fi.pa165.brown.service;

import cz.muni.fi.pa165.brown.ServiceConfig;
import cz.muni.fi.pa165.brown.dao.ReservationDao;
import cz.muni.fi.pa165.brown.entity.Hotel;
import cz.muni.fi.pa165.brown.entity.Reservation;
import cz.muni.fi.pa165.brown.entity.Room;
import cz.muni.fi.pa165.brown.entity.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Peter Hutta
 */
@ContextConfiguration(classes=ServiceConfig.class)
public class ReservationServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ReservationDao reservationDao;

    @Mock
    private TimeService timeService;

    @Mock
    private RoomService roomService;

    @Autowired
    @InjectMocks
    private ReservationService reservationService;

    private SimpleDateFormat sdf;
    private Hotel hotel;
    private Room room1;
    private Room room2;
    private List<Room> rooms;
    private Reservation reservation1;
    private Reservation reservation2;
    private List<Reservation> reservations;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void init() throws ParseException {
        User user = new User();
        user.setName("user");
        user.setSurname("surname");
        user.setAddress("address");
        user.setEmail("email");
        user.setPassword("password");
        user.setAdmin(false);

        hotel = new Hotel();
        hotel.setAddress("address");
        hotel.setName("name");
        hotel.setPhone("phone");

        room1 = new Room();
        room2 = new Room();

        room1.setHotel(hotel);
        room2.setHotel(hotel);

        room1.setCapacity(5);
        room2.setCapacity(10);

        room1.setRoomIdentifier("id1");
        room2.setRoomIdentifier("id2");

        room1.setPricePerNightPerPerson(BigDecimal.ONE);
        room2.setPricePerNightPerPerson(BigDecimal.TEN);

        rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);

        reservation1 = new Reservation();
        reservation2 = new Reservation();

        sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        reservation1.setReservedFrom(sdf.parse("24.11.2016 11:00"));
        reservation2.setReservedFrom(sdf.parse("30.11.2016 18:00"));

        reservation1.setReservedTo(sdf.parse("29.11.2016 17:00"));
        reservation2.setReservedTo(sdf.parse("31.11.2016 10:00"));

        reservation1.setRoom(room1);
        reservation2.setRoom(room2);

        reservation1.setUser(user);
        reservation2.setUser(user);

        reservationService.create(reservation1);
        reservationService.create(reservation2);

        reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);
    }

    @Test
    public void create() throws ParseException {
        Reservation reservation = new Reservation();
        reservation.setReservedFrom(sdf.parse("20.11.2016 11:00"));
        reservation.setReservedTo(sdf.parse("29.11.2016 17:00"));

        reservationService.create(reservation);
        verify(reservationDao).create(reservation);
    }

    @Test
    public void update() throws ParseException {
        reservation1.setReservedFrom(sdf.parse("25.11.2016 18:00"));

        reservationService.update(reservation1);
        when(reservationDao.findById(reservation1.getId())).thenReturn(reservation1);
        Reservation reservation = reservationService.findById(reservation1.getId());

        Assert.assertEquals(reservation.getReservedFrom(), reservation1.getReservedFrom());
    }

    @Test
    public void delete() {
        when(reservationDao.findById(reservation1.getId())).thenReturn(reservation1);
        Assert.assertNotNull(reservationService.findById(reservation1.getId()));

        when(reservationDao.findById(reservation1.getId())).thenReturn(null);
        reservationService.delete(reservation1);

        Assert.assertNull(reservationService.findById(reservation1.getId()));
    }

    @Test
    public void findById() {
        when(reservationDao.findById(reservation1.getId())).thenReturn(reservation1);
        Reservation reservation = reservationService.findById(reservation1.getId());

        Assert.assertNotNull(reservation);
        Assert.assertEquals(reservation, reservation1);
        Assert.assertNull(reservationService.findById(42L));
    }

    @Test
    public void findAll() {
        when(reservationDao.findAll()).thenReturn(reservations);
        List<Reservation> reservations = reservationService.findAll();

        Assert.assertEquals(reservations.size(), 2);
        Assert.assertTrue(reservations.contains(reservation1));
        Assert.assertTrue(reservations.contains(reservation2));
    }

    @Test
    public void findReservationsFromLastNDays() throws ParseException {
        Date today = sdf.parse("01.12.2016 12:00");
        Date sevenDaysAgo = sdf.parse("24.11.2016 12:00");

        when(timeService.getCurrentTime()).thenReturn(today);
        when(reservationDao.findReservationsBetweenDates(sevenDaysAgo, today)).thenReturn(reservations);

        List<Reservation> list = reservationService.findReservationsFromLastNDays(7);
        Assert.assertEquals(list.size(), 2);
        Assert.assertEquals(list, reservations);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findReservationsFromLastNDaysNegativeN() {
        reservationService.findReservationsFromLastNDays(-7);
    }

    @Test
    public void findReservationsBetweenDates() throws ParseException {
        Date from = sdf.parse("23.11.2016 11:00");
        Date to = sdf.parse("29.11.2016 20:00");
        List<Reservation> list1 = new ArrayList<>();
        list1.add(reservation1);

        when(reservationDao.findReservationsBetweenDates(from, to)).thenReturn(list1);
        List<Reservation> list2 = reservationService.findReservationsBetweenDates(from, to);

        Assert.assertEquals(list2.size(), 1);
        Assert.assertEquals(list2.get(0), reservation1);

        from = sdf.parse("23.11.2015 11:00");
        to = sdf.parse("29.11.2015 20:00");
        when(reservationDao.findReservationsBetweenDates(from, to)).thenReturn(new ArrayList<>());
        list2 = reservationService.findReservationsBetweenDates(from, to);
        Assert.assertEquals(list2.size(), 0);
    }

    @Test
    public void findAvailableRooms() throws ParseException {
        Date from = sdf.parse("23.11.2016 11:00");
        Date to = sdf.parse("26.11.2016 20:00");

        List<Reservation> list1 = new ArrayList<>();
        list1.add(reservation1);
        when(reservationDao.findReservationsBetweenDates(from, to)).thenReturn(list1);
        when(roomService.findByHotel(hotel)).thenReturn(rooms);

        List<Room> list2 = reservationService.findAvailableRooms(hotel, from, to);

        Assert.assertEquals(list2.size(), 1);
        Assert.assertEquals(list2.get(0), room2);

        from = sdf.parse("19.11.2016 11:00");
        to = sdf.parse("24.11.2016 10:00");
        when(reservationDao.findReservationsBetweenDates(from, to)).thenReturn(new ArrayList<>());

        list2 = reservationService.findAvailableRooms(hotel, from, to);

        Assert.assertEquals(list2.size(), 2);
        Assert.assertTrue(list2.contains(room1));
        Assert.assertTrue(list2.contains(room2));
    }
}
