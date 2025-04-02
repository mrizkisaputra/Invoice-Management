package com.mrizkisaputra.controllers;

import com.mrizkisaputra.exceptions.ResetPasswordInvalidException;
import com.mrizkisaputra.models.dto.RegisterRequestDto;
import com.mrizkisaputra.models.dto.ResetPasswordRequestDto;
import com.mrizkisaputra.models.entities.User;
import com.mrizkisaputra.models.entities.UserPassword;
import com.mrizkisaputra.repositories.UserPasswordRepository;
import com.mrizkisaputra.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

/**
 * 1. Fitur Register + reset password
 * 2. Fitur forgot password
 */
@Controller
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    // Register + Reset Password
    // =================================================================================================================
    @GetMapping(path = "/register/form")
    public String displayFormRegister(Model model) {
        model.addAttribute(new RegisterRequestDto());
        return "register/form";
    }

    @PostMapping(path = "/register/form", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processFormRegister(@ModelAttribute @Valid RegisterRequestDto requestDto,
                              BindingResult bindingResult,
                              SessionStatus sessionStatus) {

        if (bindingResult.hasErrors()) {
            return "register/form";
        }

        userService.register(requestDto);

        return "redirect:/register/success";
    }

    @GetMapping(path = "/register/success")
    public String displaySuccess() {
        return "register/success";
    }

    @GetMapping(path = "/register/verify/email")
    public String verifyEmail(@RequestParam(name = "code") String uniqueCode, Model model) {
        try {
            userService.verifyEmail(uniqueCode);
        } catch (ResetPasswordInvalidException e) {
            model.addAttribute("message", e.getMessage());
            return "password/failed";
        }

        return "redirect:/password/reset?code="+uniqueCode;
    }

    @GetMapping(path = "/password/reset")
    public String displayFormResetPassword(@RequestParam(name = "code") String uniqCode, Model model) {
        try {
            User user = userService.verifyResetPasswordLink(uniqCode);
            model.addAttribute("User", user);
            model.addAttribute(new ResetPasswordRequestDto());
        } catch (ResetPasswordInvalidException e) {
            log.warn("Reset password code invalid "+ uniqCode);
            model.addAttribute("error", "Reset password code invalid");
        }
        return "password/form_reset_password";
    }

    @PostMapping(path = "/password/reset")
    public String processFormResetPassword(@ModelAttribute ResetPasswordRequestDto dto) {
        if (dto.getConfirmPassword().equals(dto.getPassword())) {
            userService.setPassword(dto);
        } else {
            // validasi password tidak match
            return "password/form_reset_password";
        }

        return "redirect:/password/reset/success";
    }

    @GetMapping(path = "/password/reset/success")
    public String displayResetPasswordSuccess() {
        return "password/success";
    }

    // Forgot Password
    // =================================================================================================================
    @GetMapping(path = "/password/forgot")
    public String displayFormForgotPassword() {
        return "password/form_forgot_password";
    }

    @PostMapping(path = "/password/forgot", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processFormForgotPassword(@RequestParam String email) {
        userService.forgotPassword(email);
        return "redirect:/password/sent";
    }

    @GetMapping(path = "/password/sent")
    public String displayForgotPasswordSent() {
        return "/password/sent";
    }



}
