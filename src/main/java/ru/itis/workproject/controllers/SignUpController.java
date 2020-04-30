package ru.itis.workproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.workproject.dto.SignUpDto;
import ru.itis.workproject.services.SignUpService;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public ModelAndView getSignUpPage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        if (authentication != null) {
            modelAndView.setViewName("signUp");
        } else {
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ModelAndView signUp(SignUpDto form) {
        Boolean auth = signUpService.signUp(form);
        ModelAndView modelAndView = new ModelAndView();
        if (auth) {
            modelAndView.setViewName("redirect:/confirm");
        } else {
            modelAndView.setViewName("signUp");
        }
        return modelAndView;
    }
}
