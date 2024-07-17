package com.ics.springnuxt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    Logger logger = LoggerFactory.getLogger(AppController.class);

//    @Value("${spring.application.name}")
//    private String name;
//
//    @GetMapping("/")
//    public String getRoot() {
//        logger.info(name);
//        return "Hello World";
//    }
}
