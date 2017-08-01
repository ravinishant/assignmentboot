package com.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by SaiVedNish on 7/31/2017.
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "my first springboot app";
    }
}

