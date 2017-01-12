package cz.muni.fi.pa165.brown.facade;

import cz.muni.fi.pa165.brown.ServiceConfig;
import cz.muni.fi.pa165.brown.dto.hotel.HotelDTO;
import cz.muni.fi.pa165.brown.dto.reservation.ReservationDTO;
import cz.muni.fi.pa165.brown.dto.room.RoomDTO;
import cz.muni.fi.pa165.brown.dto.user.UserDTO;

import cz.muni.fi.pa165.brown.service.TimeService;
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

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;


/**
 * @author Peter Hutta
 */
@ContextConfiguration(classes = ServiceConfig.class)
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class ReservationFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ReservationFacade reservationFacade;

    @Autowired
    private HotelFacade hotelFacade;

    @Autowired
    private RoomFacade roomFacade;

    @Autowired
    private UserFacade userFacade;

    @Mock
    private TimeService timeService;

    private HotelDTO hotel;
    private RoomDTO room1;
    private RoomDTO room2;
    private ReservationDTO reservation1;
    private ReservationDTO reservation2;
    private SimpleDateFormat sdf;
    private UserDTO user;

    @BeforeClass
    public void setupMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void createReservations() throws ParseException {
        user = new UserDTO();
        user.setName("user");
        user.setSurname("surname");
        user.setAddress("address");
        user.setEmail("email");
        user.setPassword("password");
        user.setAdmin(false);
        userFacade.createUser(user);

        hotel = new HotelDTO();
        hotel.setAddress("address");
        hotel.setName("name");
        hotel.setPhone("111111111");
        hotelFacade.create(hotel);

        room1 = new RoomDTO();
        room2 = new RoomDTO();

        room1.setHotel(hotel);
        room2.setHotel(hotel);

        room1.setCapacity(5);
        room2.setCapacity(10);

        room1.setRoomIdentifier("id1");
        room2.setRoomIdentifier("id2");

        room1.setPricePerNightPerPerson(BigDecimal.ONE);
        room2.setPricePerNightPerPerson(BigDecimal.TEN);

        roomFacade.create(room1);
        roomFacade.create(room2);

        reservation1 = new ReservationDTO();
        reservation2 = new ReservationDTO();

        reservation1.setRoom(room1);
        reservation2.setRoom(room2);

        reservation1.setUser(user);
        reservation2.setUser(user);

        sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        reservation1.setReservedFrom(sdf.parse("24.11.2016 11:00"));
        reservation2.setReservedFrom(sdf.parse("30.11.2016 18:00"));

        reservation1.setReservedTo(sdf.parse("29.11.2016 17:00"));
        reservation2.setReservedTo(sdf.parse("31.11.2016 10:00"));

        reservationFacade.create(reservation1);
        reservationFacade.create(reservation2);
    }

    @Test
    public void update() throws ParseException {
        reservation1.setReservedFrom(sdf.parse("22.11.2016 11:00"));

        reservationFacade.update(reservation1);
        ReservationDTO res = reservationFacade.findById(reservation1.getId());

        Assert.assertNotNull(res);
        Assert.assertEquals(res.getReservedFrom(), reservation1.getReservedFrom());
    }

    @Test
    public void findById() {
        ReservationDTO res = reservationFacade.findById(reservation1.getId());
        Assert.assertNotNull(res);
        Assert.assertEquals(res, reservation1);
    }

    @Test
    public void findAll() {
        List<ReservationDTO> list = reservationFacade.findAll();
        Assert.assertEquals(list.size(), 2);
        Assert.assertTrue(list.contains(reservation1));
        Assert.assertTrue(list.contains(reservation2));
    }

    @Test
    public void delete() throws ParseException {
        ReservationDTO reservation = new ReservationDTO();
        reservation.setRoom(room1);
        reservation.setUser(user);

        sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        reservation.setReservedFrom(sdf.parse("24.11.2016 11:00"));
        reservation.setReservedTo(sdf.parse("25.11.2016 11:00"));
        reservationFacade.create(reservation);
        Assert.assertEquals(reservationFacade.findAll().size(), 3);
        reservationFacade.delete(reservation);
        Assert.assertEquals(reservationFacade.findAll().size(), 2);
    }

    @Test
    public void findReservationsBetweenDates() throws ParseException {
        Date from = sdf.parse("23.11.2016 11:00");
        Date to = sdf.parse("29.11.2016 16:00");
        List<ReservationDTO> list = reservationFacade.findReservationsBetweenDates(from, to);

        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0), reservation1);
    }

    @Test
    public void findAvailableRooms() throws ParseException {
        Date from = sdf.parse("23.11.2016 11:00");
        Date to = sdf.parse("26.11.2016 20:00");
        List<RoomDTO> list = reservationFacade.findAvailableRooms(hotel, from, to);

        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0), room2);
    }
}
