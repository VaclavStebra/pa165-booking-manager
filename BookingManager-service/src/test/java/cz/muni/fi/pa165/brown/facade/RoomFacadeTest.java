package cz.muni.fi.pa165.brown.facade;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.brown.ServiceConfig;
import cz.muni.fi.pa165.brown.dto.hotel.HotelDTO;
import cz.muni.fi.pa165.brown.dto.room.RoomDTO;

/**
 * Room facade tests
 *
 * @author Dominik Labuda
 */
@ContextConfiguration(classes = ServiceConfig.class)
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class RoomFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RoomFacade roomFacade;

    @Autowired
    private HotelFacade hotelFacade;

    private RoomDTO room1;
    private RoomDTO room2;
    private RoomDTO room3;

    private HotelDTO savedHotel;

    @BeforeMethod
    private void setup() {
        HotelDTO hotel = createHotelDto(
                "Hotel-1",
                "Address-1",
                "111111111"
        );

        hotelFacade.create(hotel);
        savedHotel = hotelFacade.findAll().get(0);

        room1 = createRoomDto(
                1,
                new BigDecimal("1.0"),
                savedHotel,
                "Room-1"
        );
        room2 = createRoomDto(
                2,
                new BigDecimal("2.0"),
                savedHotel,
                "Room-2"
        );
        room3 = createRoomDto(
                3,
                new BigDecimal("3.0"),
                savedHotel,
                "Room-3"
        );

        roomFacade.create(room1);
        roomFacade.create(room2);
        roomFacade.create(room3);
    }

    @Test
    public void findById() throws Exception {
        RoomDTO roomDTO = roomFacade.findById(room1.getId());
        Assert.assertNotNull(roomDTO);
        Assert.assertEquals(roomDTO.getRoomIdentifier(), room1.getRoomIdentifier());

        roomDTO = roomFacade.findById(room3.getId());
        Assert.assertNotNull(roomDTO);
        Assert.assertEquals(roomDTO.getRoomIdentifier(), room3.getRoomIdentifier());
    }

    @Test
    public void findAll() throws Exception {
        Assert.assertEquals(roomFacade.findAll().size(), 3);
    }

    @Test
    public void findByCapacity() throws Exception {
        Assert.assertEquals(roomFacade.findByCapacity(room2.getCapacity()).size(), 1);
        Assert.assertEquals(roomFacade.findByCapacity(room2.getCapacity()).get(0), room2);
    }

    @Test
    public void findByHotel() throws Exception {
        Assert.assertEquals(roomFacade.findByHotel(savedHotel).size(), 3);
    }

    @Test
    public void update() throws Exception {
        room1.setCapacity(879);
        roomFacade.update(room1);
        RoomDTO changedRoom = roomFacade.findById(room1.getId());
        Assert.assertEquals((int) changedRoom.getCapacity(), 879);
    }

    @Test
    public void delete() throws Exception {
        RoomDTO room = createRoomDto(1, new BigDecimal(12), savedHotel, "ads");
        roomFacade.create(room);
        Assert.assertEquals(roomFacade.findAll().size(), 4);
        roomFacade.delete(room);
        Assert.assertEquals(roomFacade.findAll().size(), 3);
    }

    // Helper methods

    private RoomDTO createRoomDto(Integer capacity, BigDecimal pricePerNightPerPerson, HotelDTO hotel, String roomIdentifier) {
        RoomDTO room = new RoomDTO();
        room.setCapacity(capacity);
        room.setPricePerNightPerPerson(pricePerNightPerPerson);
        room.setHotel(hotel);
        room.setRoomIdentifier(roomIdentifier);
        return room;
    }

    private HotelDTO createHotelDto(String name, String address, String phone) {
        HotelDTO hotel = new HotelDTO();
        hotel.setName(name);
        hotel.setAddress(address);
        hotel.setPhone(phone);
        return hotel;
    }
}
