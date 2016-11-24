package cz.muni.fi.pa165.brown.facade;

import cz.muni.fi.pa165.brown.ServiceConfig;
import cz.muni.fi.pa165.brown.dto.HotelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * @author Michal Hagara
 */
@ContextConfiguration(classes = ServiceConfig.class)
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class HotelFacadeTest extends AbstractTestNGSpringContextTests {

    private HotelDTO hotel1;
    private HotelDTO hotel2;

    @Autowired
    private HotelFacade hotelFacade;


    @BeforeMethod
    private void createHotels() {
        hotel1 = new HotelDTO();
        hotel2 = new HotelDTO();

        hotel1.setName("hotel1");
        hotel2.setName("hotel2");

        hotel1.setPhone("61529163");
        hotel2.setPhone("14986532");

        hotel1.setAddress("address1");
        hotel2.setAddress("address2");

        hotelFacade.create(hotel1);
        hotelFacade.create(hotel2);
    }


    @Test
    public void findById() {
        HotelDTO hotel = hotelFacade.findById(hotel1.getId());
        Assert.assertNotNull(hotel);
        Assert.assertEquals(hotel.getAddress(), hotel1.getAddress());
    }



    @Test
    public void findByAddress() {
        HotelDTO hotel = hotelFacade.findByAddress("address1");
        Assert.assertNotNull(hotel);
        Assert.assertEquals(hotel.getId(), hotel1.getId());
    }




    @Test
    public void findByWrongAddress() {
        HotelDTO hotel = hotelFacade.findByAddress("address4");
        Assert.assertNull(hotel);
    }

    @Test
    public void findAll() {
        Assert.assertEquals(hotelFacade.findAll().size(), 2);
    }

}