package com.example.unittest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaculatController {

    @GetMapping("/hello")
    String print() {
        return "world";
    }

    @PostMapping("/sum")
    int sum(int a, int b) {
        return a+b;
    }
}
