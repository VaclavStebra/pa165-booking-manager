package cz.muni.fi.pa165.brown.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import cz.muni.fi.pa165.brown.dto.user.UserDTO;
import cz.muni.fi.pa165.brown.dto.user.UserLoginDTO;
import cz.muni.fi.pa165.brown.facade.UserFacade;

/**
 * Auth controller
 *
 * @author Dominik Labuda
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    /** Logger */
    private final static Logger log = LoggerFactory.getLogger(AuthController.class);

    /** User facade */
    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String login(Model model, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return "redirect:" + uriBuilder.path("/").build().toUriString();
        }

        model.addAttribute("userLogin", new UserDTO());
        return "auth/login";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signIn(@Valid @ModelAttribute("userLogin") UserLoginDTO userLogin, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, HttpServletRequest request) {

        if(bindingResult.hasErrors()) {
            return "auth/login";
        }

        UserDTO databaseUser = userFacade.findUserByEmail(userLogin.getEmail());
        if (databaseUser == null) {
            redirectAttributes.addFlashAttribute("alert_warning", "User with email " + userLogin.getEmail() + " does not exist");
            return "redirect:" + uriBuilder.path("/auth").build().toUriString();
        }

        // Validate user password
        if (!userFacade.login(databaseUser, userLogin.getPassword())) {
            redirectAttributes.addFlashAttribute("alert_warning", "Login for " + userLogin.getEmail() + " failed");
            return "redirect:" + uriBuilder.path("/auth").build().toUriString();
        }
        request.getSession().setAttribute("user", databaseUser);
        redirectAttributes.addFlashAttribute("alert_success", "User " + userLogin.getEmail() + " has been successfully signed in");

        return "redirect:" + uriBuilder.path("/").build().toUriString();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, UriComponentsBuilder uriBuilder) {
        request.getSession().removeAttribute("user");
        return "redirect:" + uriBuilder.path("/").build().toUriString();
    }

}
