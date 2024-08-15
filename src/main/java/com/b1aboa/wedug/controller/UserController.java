package com.b1aboa.wedug.controller;

import com.b1aboa.wedug.dto.UserDTO;
import com.b1aboa.wedug.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public String updateUserProfile() {
        System.out.println("정보 수정 완료");
        return "redirect:/";
    }

    @PostMapping("/signup")
    public String signUp(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/user-signup";
        }

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.userDTO", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return "user/user-signup";
        }

        try {
            if (userService.createUser(userDTO)) {
                return "redirect:/";
            } else {
                bindingResult.rejectValue("userId", "error.userDTO", "사용자 생성에 실패했습니다.");
                return "user/user-signup";
            }
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("error.userDTO", "회원가입 중 오류가 발생했습니다.");
            return "user/user-signup";
        }
    }

    @PostMapping("/find-password")
    public String findPassword() {

        return "redirect:/";
    }

}
