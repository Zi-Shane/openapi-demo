package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {
    @GetMapping("hello")
    String helloWorld() {
        return "Hello World!";
    }
}
