package com.b1aboa.wedug.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class LoginController {

//    @GetMapping("/login")
//    public String login() {
//        return "/login";
//    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login/login";
    }

}
