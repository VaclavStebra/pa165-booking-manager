package cz.muni.fi.pa165.brown.mvc.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
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

import cz.muni.fi.pa165.brown.dto.reservation.ReservationDTO;
import cz.muni.fi.pa165.brown.dto.room.RoomDTO;
import cz.muni.fi.pa165.brown.dto.user.UserDTO;
import cz.muni.fi.pa165.brown.facade.ReservationFacade;
import cz.muni.fi.pa165.brown.facade.RoomFacade;
import cz.muni.fi.pa165.brown.facade.UserFacade;
import cz.muni.fi.pa165.brown.mvc.binders.DateBinder;
import cz.muni.fi.pa165.brown.mvc.binders.RoomBinder;
import cz.muni.fi.pa165.brown.mvc.binders.UserBinder;

/**
 * Reservations controller
 *
 * @author Dominik Labuda
 */
@Controller
@RequestMapping("/reservations")
public class ReservationsController {

    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(ReservationsController.class);

    /** Reservation facade */
    @Autowired
    private ReservationFacade reservationFacade;

    /** User facade */
    @Autowired
    private UserFacade userFacade;

    /** Room facade */
    @Autowired
    private RoomFacade roomFacade;

    @Autowired
    private RoomBinder roomBinder;

    @Autowired
    private UserBinder userBinder;

    @Autowired
    private DateBinder dateBinder;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("List operation called");
        model.addAttribute("reservations", reservationFacade.findAll());
        return "reservations/list";
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String show(@PathVariable long id, Model model) {
        model.addAttribute("reservation", reservationFacade.findById(id));
        return "reservations/show";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createReservation(Model model) {
        log.debug("Create get was called");
        model.addAttribute("reservationNew", new ReservationDTO());
        model.addAttribute("rooms", roomFacade.findAll());
        model.addAttribute("users", userFacade.getAllUsers());
        return "reservations/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("reservationNew") ReservationDTO reservationNew, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userFacade.getAllUsers());
            model.addAttribute("rooms", roomFacade.findAll());
            return "reservations/create";
        }
        reservationFacade.create(reservationNew);
        redirectAttributes.addFlashAttribute("alert_success", "Creation of reservation for " + reservationNew.getUser().getEmail() + " succeeded");

        return "redirect:" + uriBuilder.path("/reservations").build().toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editRoom(@PathVariable long id, Model model) {
        ReservationDTO reservationDTO = reservationFacade.findById(id);

        model.addAttribute("reservationEdit", reservationDTO);
        model.addAttribute("users", userFacade.getAllUsers());
        model.addAttribute("rooms", roomFacade.findAll());
        return "reservations/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("reservationEdit") ReservationDTO reservationEdit,
                       BindingResult bindingResult,
                       @PathVariable long id,
                       Model model,
                       UriComponentsBuilder uriBuilder,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userFacade.getAllUsers());
            model.addAttribute("rooms", roomFacade.findAll());
            return "reservations/edit";
        }

        reservationEdit.setId(id);
        reservationFacade.update(reservationEdit);

        redirectAttributes.addFlashAttribute("alert_success", "Update of reservation for " + reservationEdit.getUser().getEmail() + " was successful");
        return "redirect:" + uriBuilder.path("/reservations").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        UserDTO loggedUser = (UserDTO) request.getSession().getAttribute("user");
        if(!loggedUser.isAdmin()){
            return "redirect:" + uriBuilder.path("/").build().toUriString();
        }

        ReservationDTO reservation = reservationFacade.findById(id);
        reservationFacade.delete(reservation);
        redirectAttributes.addFlashAttribute("alert_success", "Reservation for \"" + reservation.getUser().getEmail() + "\" has been deleted.");
        return "redirect:" + uriBuilder.path("/reservations").build().toUriString();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(RoomDTO.class, this.roomBinder);
        binder.registerCustomEditor(UserDTO.class, this.userBinder);
        binder.registerCustomEditor(Date.class, this.dateBinder);
    }

}
