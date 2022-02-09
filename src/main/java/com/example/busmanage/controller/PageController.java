package com.example.busmanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("page")
@Controller
public class PageController {

    @GetMapping("{page}.html")
    public String page(@PathVariable String page) {
        return page;
    }

    @GetMapping("{page1}/{page2}.html")
    public String page(@PathVariable String page1, @PathVariable String page2) {
        return page1 + "/" + page2;
    }
}
