package com.example.springmarket.controller2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnotherInfController {
    @GetMapping("/about")
    public String getAboutUs(){
        return "aboutUs";
    }
}
