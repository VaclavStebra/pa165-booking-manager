package cz.muni.fi.pa165.brown.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.brown.ServiceConfig;
import cz.muni.fi.pa165.brown.dao.RoomDao;
import cz.muni.fi.pa165.brown.entity.Hotel;
import cz.muni.fi.pa165.brown.entity.Room;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link cz.muni.fi.pa165.brown.service.RoomService}
 *
 * @author Dominik Labuda
 */
@ContextConfiguration(classes = ServiceConfig.class)
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class RoomServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private RoomDao roomDao;

    @Autowired
    @InjectMocks
    private RoomService roomService;

    private final static int ROOM_COUNT = 3;

    private List<Room> rooms;

    private Hotel fakeHotel;
    private Hotel hotel;

    @BeforeMethod
    public void setup() {
        rooms = new ArrayList<>();

        hotel = createHotel(
                1L,
                "Hotel-1",
                "Address-1",
                "Phone-1"
        );

        for (int i = 1; i <= ROOM_COUNT; i++) {
            Room room = createRoom(
                    new Long(i),
                    new Integer(i),
                    new BigDecimal(5 * i),
                    hotel,
                    "Identifier-" + i
            );
            rooms.add(room);
            roomService.create(room);
        }

        for (int i = 0; i < ROOM_COUNT; i++) {
            when(roomDao.findById(rooms.get(i).getId())).thenReturn(rooms.get(i));
            when(roomDao.findByCapacity(rooms.get(i).getCapacity())).thenReturn(Collections.singletonList(rooms.get(i)));
        }

        when(roomDao.findByHotel(hotel)).thenReturn(rooms);
        when(roomDao.findAll()).thenReturn(rooms);

        when(roomDao.findById(666L)).thenReturn(null);
        when(roomDao.findByCapacity(666)).thenReturn(null);

        fakeHotel = createHotel(
                666L,
                "ABC",
                "DEF",
                "GEH"
        );
        when(roomDao.findByHotel(fakeHotel)).thenReturn(null);
    }

    @BeforeClass
    public void setupMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findById() throws Exception {
        Room room = roomService.findById(rooms.get(0).getId());
        Assert.assertNotNull(room);
        Assert.assertEquals(rooms.get(0).getId(), room.getId());
        Assert.assertNull(roomService.findById(123456789L));
    }

    @Test
    public void findAll() throws  Exception {
        List<Room> rooms = roomService.findAll();
        Assert.assertNotNull(rooms);
        Assert.assertEquals(rooms, this.rooms);
    }

    @Test
    public void findByCapacity() throws Exception {
        List<Room> room = roomService.findByCapacity(rooms.get(1).getCapacity());
        Assert.assertNotNull(room);
        Assert.assertEquals(room.size(), 1);
        Assert.assertNull(roomService.findByCapacity(666));
    }

    @Test
    public void findByHotel() throws Exception {
        List<Room> room = roomService.findByHotel(hotel);
        Assert.assertNotNull(room);
        Assert.assertEquals(room.size(), 3);
        Assert.assertNull(roomService.findByHotel(fakeHotel));
    }

    @Test
    public void update() throws Exception {
        rooms.get(1).setCapacity(123123);
        roomService.update(rooms.get(1));
        Assert.assertEquals((int)roomService.findById(rooms.get(1).getId()).getCapacity(), 123123);
    }

    @Test
    public void delete() throws Exception {
        Room room = rooms.get(2);
        rooms.remove(2);
        roomService.delete(rooms.get(0));
        when(roomDao.findAll()).thenReturn(rooms);
        Assert.assertEquals(roomService.findAll().size(), ROOM_COUNT - 1);
        rooms.add(2, room);
    }

    public void create() throws Exception {
        Room room = createRoom(
                null,
                999,
                new BigDecimal(999),
                hotel,
                "Identifier-" + 999
        );

        roomService.create(room);
        verify(roomDao).create(room);
    }

    private Room createRoom(Long id, Integer capacity, BigDecimal pricePerNightPerPerson, Hotel hotel, String roomIdentifier) {
        Room room = new Room();
        room.setId(id);
        room.setCapacity(capacity);
        room.setPricePerNightPerPerson(pricePerNightPerPerson);
        room.setHotel(hotel);
        room.setRoomIdentifier(roomIdentifier);
        return room;
    }

    private Hotel createHotel(Long id, String name, String address, String phone) {
        Hotel hotel = new Hotel();
        hotel.setId(id);
        hotel.setName(name);
        hotel.setAddress(address);
        hotel.setPhone(phone);
        return hotel;
    }
}
