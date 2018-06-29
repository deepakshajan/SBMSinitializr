package com.initializr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LandingPageController {

    @RequestMapping(value="/")
    public ModelAndView loadLandingPage() {

        ModelAndView model = new ModelAndView("landingPage");
        return model;
    }
}
