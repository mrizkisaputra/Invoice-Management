package com.mrizkisaputra.controllers;

import com.mrizkisaputra.models.entities.Customer;
import com.mrizkisaputra.repositories.CustomerRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/customer")
@Slf4j
public class CustomerWebController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(path = "/list")
    public ModelMap dataCustomer(@SortDefault({"name"}) Pageable pageable) {
        return new ModelMap()
                .addAttribute("Customers", customerRepository.findAll(pageable));
    }

    @GetMapping(path = "/form/{id}")
    public String displayForm(@PathVariable("id") Customer customer, Model model) {
        model.addAttribute("customer", customer);
        return "customer/form";
    }

    @PostMapping(path = "/form", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processForm(@ModelAttribute @Valid Customer customer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "customer/form";
        }

        customerRepository.save(customer);

        return "redirect:/customer/list";
    }
}
