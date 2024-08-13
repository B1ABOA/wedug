package com.b1aboa.wedug.service;

import com.b1aboa.wedug.entity.User;
import com.b1aboa.wedug.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements  UserService{
    private final UserRepository userRepository;

    public User create(String userId, String nickname, String password, char gender, int birthYear, int nation){
        User user = new User();
        user.setUserId(userId);
        user.setNickname(nickname);
        return user;
    }

}
