package com.b1aboa.wedug.controller;

import com.b1aboa.wedug.dto.UserDTO;
import com.b1aboa.wedug.entity.User;
import com.b1aboa.wedug.jwt.JWTUtil;
import com.b1aboa.wedug.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    private JWTUtil jwtUtil;

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

    @GetMapping("/info")
    public ResponseEntity<UserDTO> getUserInfo(Authentication authentication) {
        String userId = authentication.getName();
        log.info(userId+ "유저아이디로그찍기");
        UserDTO userDto = userService.getUserInfo(userId);
        return ResponseEntity.ok(userDto);
    }

//    @PostMapping("/update")
//    public ResponseEntity<String> updateUserInfo(@RequestBody UserDTO userUpdateDto) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        jwtUtil.getUsername(userUpdateDto.getUserId());
//        String userId = authentication.getName();
//
//        UserDTO userDto = userService.getUserInfo(userId);
//        if (userDto == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//
//        userDto.setUserId(userUpdateDto.getUserId());
//        userDto.setNickname(userUpdateDto.getNickname());
//        userDto.setGender(userUpdateDto.getGender());
//        userDto.setBirthYear(userUpdateDto.getBirthYear());
//        userDto.setNationCode(userUpdateDto.getNationCode());
//
//        userService.updateUser(userDto);
//
//        return ResponseEntity.ok("User info updated successfully");
//    }

    @PostMapping("/update")
    public ResponseEntity<String> updateUserInfo(@RequestBody UserDTO userUpdateDto, Authentication authentication) {
        String userId = authentication.getName();
        if (!userId.equals(userUpdateDto.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can only update your own information");
        }

        try {
            userService.updateUser(userUpdateDto);
            return ResponseEntity.ok("User info updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
