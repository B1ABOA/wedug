package com.b1aboa.wedug.controller;

import com.b1aboa.wedug.dto.UserDTO;
import com.b1aboa.wedug.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

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
    public String signUp(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "user/user-signup";
        }
        if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            return "user/user-signup";
        }
        //userService.create(userDTO.getUserId(),userDTO.getNickname(),userDTO.getPassword(),userDTO.getGender(),userDTO.getBirthYear(),userDTO.getCountry());
        if(userService.createUser(userDTO)){
            System.out.println("회원가입 축하뽀카");
        };
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
