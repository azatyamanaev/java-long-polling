package ru.itis.workproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.workproject.services.LogInService;


@Controller
public class LogInController {

    @Autowired
    private LogInService logInService;

    @RequestMapping(value = "/logIn", method = RequestMethod.GET)
    public ModelAndView getLogInPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("logIn");
        return modelAndView;
    }
}
