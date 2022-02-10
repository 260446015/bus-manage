package com.example.busmanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    @ResponseBody
    public ModelAndView index(String page,ModelAndView modelAndView){
        modelAndView.addObject("page",page);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
