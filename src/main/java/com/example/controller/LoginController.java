package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/loginFailure")
    public String loginFailure() {
        return "error";
    }
	
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
