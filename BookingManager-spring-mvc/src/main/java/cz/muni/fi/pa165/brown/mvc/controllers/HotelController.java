package cz.muni.fi.pa165.brown.mvc.controllers;



import cz.muni.fi.pa165.brown.dto.hotel.HotelDTO;
import cz.muni.fi.pa165.brown.dto.room.RoomDTO;
import cz.muni.fi.pa165.brown.dto.user.UserDTO;
import cz.muni.fi.pa165.brown.facade.HotelFacade;
import cz.muni.fi.pa165.brown.facade.RoomFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vaclav Stebra
 */

@Controller
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelFacade hotelFacade;

    @Autowired
    private RoomFacade roomFacade;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(Model model) {
        List<HotelDTO> hotels = hotelFacade.findAll();
        model.addAttribute("hotels", hotels);

        Map<Long, List<RoomDTO>> roomsForHotel = new HashMap<>();
        for (HotelDTO hotel : hotels) {
            roomsForHotel.put(hotel.getId(), roomFacade.findByHotel(hotel));
        }
        model.addAttribute("roomsForHotel", roomsForHotel);
        return "hotels/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {
        HotelDTO hotel = hotelFacade.findById(id);
        List<RoomDTO> availableRooms = roomFacade.findByHotel(hotel);

        model.addAttribute("hotel", hotel);
        model.addAttribute("rooms", availableRooms);

        return "hotels/show";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createHotel(Model model) {
        model.addAttribute("hotel", new HotelDTO());
        return "hotels/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("hotel") HotelDTO hotel, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            return "hotels/create";
        }
        hotelFacade.create(hotel);
        redirectAttributes.addFlashAttribute("alert_success", "Creation of " + hotel.getName() + " succeeded");

        return "redirect:" + uriBuilder.path("/hotels").build().toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editHotel(@PathVariable long id, Model model) {
        HotelDTO hotel = hotelFacade.findById(id);

        model.addAttribute("hotelEdit", hotel);
        return "hotels/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("hotelEdit") HotelDTO hotel,
                       BindingResult bindingResult,
                       @PathVariable long id,
                       Model model,
                       UriComponentsBuilder uriBuilder,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "hotels/edit";
        }

        hotel.setId(id);
        hotelFacade.update(hotel);

        redirectAttributes.addFlashAttribute("alert_success", "Hotel " + hotel.getName() + " was updated");
        return "redirect:" + uriBuilder.path("/hotels").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        UserDTO loggedUser = (UserDTO) request.getSession().getAttribute("user");
        if(!loggedUser.isAdmin()){
            return "redirect:" + uriBuilder.path("/").build().toUriString();
        }

        HotelDTO hotel = hotelFacade.findById(id);
        hotelFacade.delete(hotel);
        redirectAttributes.addFlashAttribute("alert_success", "Hotel \"" + hotel.getName() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/hotels").build().toUriString();
    }
}
