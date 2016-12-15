package cz.muni.fi.pa165.brown.mvc.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import cz.muni.fi.pa165.brown.dto.hotel.HotelDTO;
import cz.muni.fi.pa165.brown.dto.room.RoomDTO;
import cz.muni.fi.pa165.brown.facade.HotelFacade;
import cz.muni.fi.pa165.brown.facade.RoomFacade;
import cz.muni.fi.pa165.brown.mvc.binders.HotelBinder;

/**
 * Rooms controller
 *
 * @author Dominik Labuda
 */
@Controller
@RequestMapping("/rooms")
public class RoomsController {

    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(RoomsController.class);

    /** Room facade */
    @Autowired
    private RoomFacade roomFacade;

    /** Hotel facade */
    @Autowired
    private HotelFacade hotelFacade;

    /** Binds HotelDTO entity */
    @Autowired
    private HotelBinder hotelBinder;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("List operation called");
        model.addAttribute("rooms", roomFacade.findAll());
        return "rooms/list";
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String show(@PathVariable long id, Model model) {
        model.addAttribute("room", roomFacade.findById(id));
        return "rooms/show";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createRoom(Model model) {
        log.debug("Create get was called");
        model.addAttribute("roomNew", new RoomDTO());
        model.addAttribute("hotels", hotelFacade.findAll());
        return "rooms/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("roomNew") RoomDTO roomNew, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("hotels", hotelFacade.findAll());
            return "rooms/create";
        }
        roomFacade.create(roomNew);
        redirectAttributes.addFlashAttribute("alert_success", "Creation of " + roomNew.getRoomIdentifier() + " succeeded");

        return "redirect:" + uriBuilder.path("/rooms").build().toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editRoom(@PathVariable long id, Model model) {
        RoomDTO roomDTO = roomFacade.findById(id);

        model.addAttribute("roomEdit", roomDTO);
        model.addAttribute("hotels", hotelFacade.findAll());
        return "rooms/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("roomEdit") RoomDTO roomEdit,
                       BindingResult bindingResult,
                       @PathVariable long id,
                       Model model,
                       UriComponentsBuilder uriBuilder,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("hotels", hotelFacade.findAll());
            return "rooms/edit";
        }

        roomEdit.setId(id);
        roomFacade.update(roomEdit);

        redirectAttributes.addFlashAttribute("alert_success", "Room " + roomEdit.getRoomIdentifier() + " was updated");
        return "redirect:" + uriBuilder.path("/rooms").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {

        RoomDTO room = roomFacade.findById(id);
        roomFacade.delete(room);
        redirectAttributes.addFlashAttribute("alert_success", "Room \"" + room.getRoomIdentifier() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/rooms").build().toUriString();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(HotelDTO.class, this.hotelBinder);
    }
}
