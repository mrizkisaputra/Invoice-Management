package com.mrizkisaputra.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationWebController {

    @GetMapping(path = "/register")
    public String register() {
        return "register";
    }
}
