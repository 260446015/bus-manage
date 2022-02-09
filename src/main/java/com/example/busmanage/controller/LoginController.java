package com.example.busmanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/auth/login")
    public String loginPage(Model model){
        model.addAttribute("loginProcessUrl","/login");
        return "login";
    }

    @GetMapping
    public String main(){
        return "index";
    }

    @GetMapping("index.html")
    public String index(){
        return "index";
    }
}
