package com.example.busmanage.controller;

import com.example.busmanage.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/auth/login")
    public String loginPage(Model model){
        model.addAttribute("loginProcessUrl","/api/auth/login");
        return "login";
    }

    @GetMapping
    public String main(){
        return "index";
    }

    @GetMapping("index.html")
    @ResponseBody
    public ModelAndView index(String page,ModelAndView modelAndView){
        modelAndView.addObject("page", StringUtils.isEmpty(page)?"content":page);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
