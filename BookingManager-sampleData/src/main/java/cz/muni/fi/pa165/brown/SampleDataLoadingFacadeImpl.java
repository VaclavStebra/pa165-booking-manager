package cz.muni.fi.pa165.brown;

import cz.muni.fi.pa165.brown.entity.*;
import cz.muni.fi.pa165.brown.service.HotelService;
import cz.muni.fi.pa165.brown.service.RoomService;
import cz.muni.fi.pa165.brown.service.UserService;
import cz.muni.fi.pa165.brown.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of {@link SampleDataLoadingFacade}.
 *
 * @author michal hagara
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;

    Reservation reservation1;
    Reservation reservation2;
    Reservation reservation3;
    Reservation reservation4;


    Room room1;
    Room room2;
    Room room3;
    Room room4;
    Room room5;

    Hotel hotel1;
    Hotel hotel2;


    User user1;
    User user2;
    User user3;


    @Override
    public void loadData() throws IOException, ParseException {

        hotel1 = hotel("Hotel Popelka");
        hotel2 = hotel("Hotel Brilliant");

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = simpleDate.parse("2020-05-03");
        Date date2 = simpleDate.parse("2020-05-10");
        Date date3 = simpleDate.parse("2020-05-08");
        Date date4 = simpleDate.parse("2020-05-18");
        Date date5 = simpleDate.parse("2020-05-21");

        user1 = user("Paolo", "Jennings", "paolo@jennings.com");
        user2 = user("Dan", "Carter", "dan@carter.com");
        user3 = user("Maa", "Nonu", "maa@nonu");

        room1 = room(1, new BigDecimal(40), 2, hotel1);
        room2 = room(2, new BigDecimal(100), 3, hotel1);
        room3 = room(3, new BigDecimal(150), 2, hotel1);
        room4 = room(4, new BigDecimal(20), 4, hotel2);
        room5 = room(5, new BigDecimal(70), 2, hotel2);



        reservation1 = reservation(date1, date2, user1, room1);
        reservation2 = reservation(date1, date2, user2, room2);
        reservation3 = reservation(date4, date5, user2, room2);
        reservation4 = reservation(date3, date4, user3, room4);

    }

    private Room room(int number, BigDecimal price, int capacity, Hotel hotel) {
        Room room = new Room();
        room.setHotel(hotel);
        room.setCapacity(capacity);
        room.setPricePerNightPerPerson(price);
        roomService.create(room);

        return room;
    }

    private Hotel hotel(String name) {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotelService.create(hotel);

        return hotel;
    }

    private Reservation reservation(Date dateFrom, Date dateTo, User user, Room room) {
        Reservation reservation = new Reservation();
        reservation.setReservedFrom(dateFrom);
        reservation.setReservedTo(dateTo);
        reservation.setUser(user);
        reservation.setRoom(room);
        reservationService.create(reservation);

        return reservation;
    }

    private User user(String name, String surname, String email) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword("superandompassword");
        user.setAdmin(false);
        userService.createUser(user);

        return user;
    }
}
