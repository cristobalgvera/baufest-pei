package com.baufest.tennis.springtennis.controller;

import com.baufest.tennis.springtennis.service.CanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springtennis/api/v1/canchas")
public class CanchaController {

    @Autowired
    private CanchaService canchaService;

}
