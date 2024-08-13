package com.b1aboa.wedug.service;

import com.b1aboa.wedug.dto.UserDTO;
import com.b1aboa.wedug.entity.User;
import com.b1aboa.wedug.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements  UserService{
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    public User create(String userId, String nickname, String password, char gender, int birthYear, int nation){
        User user = new User();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(password));
        user.setNickname(nickname);
        return user;
    }
    public boolean createUser(UserDTO userDTO) {
        if (userDTO == null) return false;

        User user = new User();
        if(userRepository.findById(userDTO.getUserId()).isPresent()){
            System.out.println("아이디 중복");
            return false;
        }
        user.setUserId(userDTO.getUserId());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setNickname(userDTO.getNickname());
        user.setGender(userDTO.getGender());
        user.setBirthYear(userDTO.getBirthYear());
        user.setNotionCode(userDTO.getNationInfo());

        userRepository.save(user);
        return true;
    }

}
