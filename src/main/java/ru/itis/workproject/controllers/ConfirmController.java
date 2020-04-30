package ru.itis.workproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.workproject.services.ConfirmUser;
import ru.itis.workproject.services.EmailSenderImpl;

@Controller
public class ConfirmController {

    @Autowired
    private ConfirmUser confirmUser;

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ModelAndView getConfirmPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("confirm");
        return modelAndView;
    }

    @RequestMapping(value = "/confirming", method = RequestMethod.GET)
    public ModelAndView confirmUser(@org.jetbrains.annotations.NotNull String key, @org.jetbrains.annotations.NotNull String login) {
        ModelAndView modelAndView = new ModelAndView();
        if (key.equals(EmailSenderImpl.getKey())) {
            confirmUser.confirm(login);
            modelAndView.setViewName("redirect:/profile");
        } else {
            modelAndView.setViewName("redirect:/confirm_error");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/confirm_error", method = RequestMethod.GET)
    public ModelAndView getConfirmErrorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("confirm_error");
        return modelAndView;
    }
}
