package com.hei.tsinjo.endpoint.rest.controller;

import com.hei.tsinjo.model.Donation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class DonationController {

    @GetMapping("/")
    public String index() {

        return "index";
    }

}
