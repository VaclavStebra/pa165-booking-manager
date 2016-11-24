package cz.muni.fi.pa165.brown.service;


import cz.muni.fi.pa165.brown.BeanMappingService;
import cz.muni.fi.pa165.brown.ServiceConfig;
import cz.muni.fi.pa165.brown.dao.HotelDao;
import cz.muni.fi.pa165.brown.entity.Hotel;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by michal.hagara
 */




@ContextConfiguration(classes = ServiceConfig.class)
public class HotelServiceTest extends AbstractTestNGSpringContextTests {


    @Mock
    private HotelDao hotelDao;

    @InjectMocks
    @Autowired
    private HotelService hotelService;


    @Autowired
    BeanMappingService mapper;


    private Hotel hotel1;
    private Hotel hotel2;

    public HotelServiceTest() {
    }


    @BeforeMethod
    public void createHotel() {
        MockitoAnnotations.initMocks(this);
        hotel1 = new Hotel();
        hotel1.setAddress("address1");
        hotel1.setName("hotel1");
        hotel1.setPhone("9642881");

        hotel2 = new Hotel();
        hotel2.setAddress("address2");
        hotel2.setName("hotel2");
        hotel2.setPhone("46546813");

        hotelService.create(hotel1);
        hotelService.create(hotel2);


        List<Hotel> hotels = new ArrayList<>();
        hotels.add(hotel1);
        hotels.add(hotel2);
        when(hotelDao.findAll()).thenReturn(hotels);

    }

    @Test
    public void testCreate() {
        Hotel h = new Hotel();
        h.setName("hotel");

        h.setAddress("address");
        h.setPhone("198625655");

        hotelService.create(h);
        verify(hotelDao).create(h);
    }




    @Test
    public void testUpdate() {
        hotel1.setName("NewHotel");
        hotelService.update(hotel1);
        when(hotelDao.findById(hotel1.getId())).thenReturn(hotel1);
        Assert.assertEquals(hotelService.findById(hotel1.getId()).getName(), hotel1.getName());
    }

    @Test
    public void testDeleteHotel() {

        when(hotelDao.findById(hotel1.getId())).thenReturn(hotel1);
        Assert.assertNotNull(hotelService.findById(hotel1.getId()));

        hotelService.delete(hotel1);
        when(hotelDao.findById(hotel1.getId())).thenReturn(null);
        Assert.assertNull(hotelService.findById(hotel1.getId()));
    }

    @Test
    public void testFindHotelById() {
        when(hotelDao.findById(hotel1.getId())).thenReturn(hotel1);
        Hotel hotelCompare = hotelService.findById(hotel1.getId());
        Assert.assertEquals(hotelCompare, hotel1);
    }

    @Test
    public void testFindHotelByAddress() {
        when(hotelDao.findByAddress("address1")).thenReturn(hotel1);
        hotelService.findByAddress("address1");
        Assert.assertEquals(hotel1.getAddress(), "address1");
    }


    @Test
    public void findAllHotels() {
        Collection<Hotel> hotels = hotelService.findAll();
        Assert.assertEquals(hotels.size(), 2);
    }

}