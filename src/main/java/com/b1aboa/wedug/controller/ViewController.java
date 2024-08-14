package com.b1aboa.wedug.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/auth/login")
    public String showLoginPage() {
        System.out.println("로그인화면");
        return "login/login";
    }

    @GetMapping("/user")
    public String showUserPage() {

        return "user/user-profile";
    }

    @GetMapping("/user/signup")
    public String showSignUpPage() {

        return "user/user-signup";
    }

    @GetMapping("/user/find-password")
    public String showFindPasswordPage() {

        return "user/user-find-password";
    }

    @GetMapping("/favorites")
    public String showFavoritePage() {
        return "favorites/favorites";
    }

}