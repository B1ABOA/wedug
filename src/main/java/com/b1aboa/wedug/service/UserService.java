package com.b1aboa.wedug.service;

import com.b1aboa.wedug.dto.UserDTO;
import com.b1aboa.wedug.entity.User;

public interface UserService {
    public User create(String userId, String nickname, String password, char gender, int birthYear, int nation);

    public boolean createUser(UserDTO userDTO);


}
