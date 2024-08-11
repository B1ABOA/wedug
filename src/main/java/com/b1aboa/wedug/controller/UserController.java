package com.b1aboa.wedug.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String showUserPage() {

        return "user/user-profile";
    }

    @PostMapping
    public String updateUserProfile() {
        System.out.println("정보 수정 완료");
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String showSignUpPage() {

        return "user/user-signup";
    }

    @PostMapping("/signup")
    public String signUp() {
        System.out.println("회원가입 축하뽀카");
        return "redirect:/";
    }

    @GetMapping("/find-password")
    public String showFindPasswordPage() {

        return "user/user-find-password";
    }

    @PostMapping("/find-password")
    public String findPassword() {

        return "redirect:/";
    }

}
