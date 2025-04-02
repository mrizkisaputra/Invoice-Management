package com.mrizkisaputra.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeWebController {

    @GetMapping(path = {"/", "/home"})
    public String home() {
        return "home";
    }
}
