package cz.muni.fi.pa165.brown.mvc.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cz.muni.fi.pa165.brown.dto.reservation.ReservationCreateDTO;
import cz.muni.fi.pa165.brown.dto.reservation.ReservationIntervalDTO;
import cz.muni.fi.pa165.brown.mvc.validators.ReservationCreateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
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
    private ReservationCreateValidator validator;

    @Autowired
    private RoomBinder roomBinder;

    @Autowired
    private UserBinder userBinder;

    @Autowired
    private DateBinder dateBinder;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, HttpServletRequest request ) {
        log.debug("List operation called");
        ReservationIntervalDTO interval = new ReservationIntervalDTO();
        interval.setFrom(new Date());
        interval.setTo(new Date());
        model.addAttribute("intervalNew", new ReservationIntervalDTO());
        UserDTO loggedUser = (UserDTO) request.getSession().getAttribute("user");
        List<ReservationDTO> reservations;
        if (loggedUser == null) {
            reservations = new ArrayList<>();
        } else if (loggedUser.isAdmin()){
            reservations = reservationFacade.findAll();
        } else {
            reservations = reservationFacade.findForUser(loggedUser);
        }
        model.addAttribute("reservations", reservations);
        return "reservations/list";
    }

    @RequestMapping(value="/interval", method = RequestMethod.GET)
    public String listInterval (@Valid @ModelAttribute("intervalNew") ReservationIntervalDTO interval, BindingResult bindingResult, Model model,
                                RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("alert_warning", "Wrong date format - please enter dates in dd.MM.yyyy HH:mm format");
            return "redirect:" + uriBuilder.path("/reservations").build().toUriString();
        }
        model.addAttribute("interval", interval);
        model.addAttribute("reservations", reservationFacade.findReservationsBetweenDates(interval.getFrom(), interval.getTo()));
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

    @RequestMapping(value = "", method = RequestMethod.POST)
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

    @RequestMapping(value = "/new/{roomId}", method = RequestMethod.GET)
    public String createReservationByUser(@PathVariable Long roomId, Model model, HttpServletRequest request) {
        log.debug("Create get was called");

        ReservationCreateDTO reservation = new ReservationCreateDTO();
        model.addAttribute("reservation", reservation);
        model.addAttribute("room", roomFacade.findById(roomId));
        return "reservations/new";
    }

    @RequestMapping(value = "/new/{roomId}", method = RequestMethod.POST)
    public String createByUser(@PathVariable Long roomId, @ModelAttribute("reservation") ReservationCreateDTO reservation,
                               BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                               UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        reservation.setRoomId(roomId);
        validator.validate(reservation, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("room", roomFacade.findById(roomId));
            return "reservations/new";
        }

        ReservationDTO newReservation = new ReservationDTO();
        newReservation.setRoom(roomFacade.findById(roomId));
        newReservation.setUser((UserDTO)request.getSession().getAttribute("user"));
        newReservation.setReservedFrom(reservation.getReservedFrom());
        newReservation.setReservedTo(reservation.getReservedTo());

        reservationFacade.create(newReservation);
        redirectAttributes.addFlashAttribute("alert_success", "Creation of reservation for " + newReservation.getUser().getEmail() + " succeeded");

        return "redirect:" + uriBuilder.path("/reservations/get/" + newReservation.getId()).build().toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model, HttpServletRequest request) {
        ReservationDTO reservationDTO = reservationFacade.findById(id);
        UserDTO loggedUser = (UserDTO)request.getSession().getAttribute("user");
        if ( ! loggedUser.isAdmin()) {
            ReservationIntervalDTO reservationEdit = new ReservationIntervalDTO();

            reservationEdit.setFrom(reservationDTO.getReservedFrom());
            reservationEdit.setTo(reservationDTO.getReservedTo());

            model.addAttribute("reservationEdit", reservationEdit);
            model.addAttribute("method", "editUser");
            model.addAttribute("reservationId", id);
            return "reservations/edituser";
        } else {
            model.addAttribute("reservationEdit", reservationDTO);
            model.addAttribute("users", userFacade.getAllUsers());
            model.addAttribute("rooms", roomFacade.findAll());
            model.addAttribute("method", "edit");
            return "reservations/edit";
        }
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

    @RequestMapping(value = "/editUser/{id}", method = RequestMethod.POST)
    public String editUser(@Valid @ModelAttribute("reservationEdit") ReservationIntervalDTO reservationEdit,
                           BindingResult bindingResult,
                           @PathVariable long id,
                           Model model,
                           UriComponentsBuilder uriBuilder,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("reservationId", id);
            return "reservations/edituser";
        }

        ReservationDTO reservation = reservationFacade.findById(id);
        reservation.setReservedFrom(reservationEdit.getFrom());
        reservation.setReservedTo(reservationEdit.getTo());
        reservationFacade.update(reservation);

        redirectAttributes.addFlashAttribute("alert_success", "Update of reservation for " + reservation.getUser().getEmail() + " was successful");
        return "redirect:" + uriBuilder.path("/reservations").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ReservationDTO res = reservationFacade.findById(id);
        UserDTO loggedUser = (UserDTO) request.getSession().getAttribute("user");
        if(!loggedUser.isAdmin() && res.getUser().getId() != loggedUser.getId()){
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        //binder.registerCustomEditor(Date.class, this.dateBinder);
    }

}
