package com.example.tournament.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LandingController {
    @GetMapping("/")
    public String main(){
        return "main";
    }
    @GetMapping("/success")
    public String success(){
        return "success";
    }
}
