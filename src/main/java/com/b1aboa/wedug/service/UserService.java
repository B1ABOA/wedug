package com.b1aboa.wedug.service;

import com.b1aboa.wedug.dto.UserDTO;



public interface UserService {
    boolean createUser(UserDTO userDTO);

    UserDTO getUserInfo(String userId);

    void updateUser(UserDTO userDto);
}
