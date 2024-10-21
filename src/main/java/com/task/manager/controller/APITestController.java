package com.task.manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APITestController {

    @GetMapping("/api-test")
    private String test() {
        return "Success";
    }

    @GetMapping("/welcome-test")
    public String welcomeTest(@RequestParam(name = "nome") String name) {
        return "Olá " + name + ", Seja muito bem vindo! ";
    }

}
