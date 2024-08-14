package com.b1aboa.wedug.service;

import com.b1aboa.wedug.dto.UserDTO;
import com.b1aboa.wedug.entity.NationInfo;
import com.b1aboa.wedug.entity.User;
import com.b1aboa.wedug.repository.NationInfoRepository;
import com.b1aboa.wedug.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
public class UserServiceImpl implements  UserService{
    private final UserRepository userRepository;
    private final NationInfoRepository nationInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(UserDTO userDTO) {
        if (userDTO == null) return false;

        User user = new User();
        NationInfo nationInfo = new NationInfo();

        if(userRepository.findById(userDTO.getUserId()).isPresent()){
            System.out.println("아이디 중복");
            return false;
        }

        user.setUserId(userDTO.getUserId());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setNickname(userDTO.getNickname());
        user.setGender(userDTO.getGender());
        user.setBirthYear(userDTO.getBirthYear());

        nationInfo = nationInfoRepository.getReferenceById(userDTO.getNationCode());

        user.setNationCode(nationInfo);

        userRepository.save(user);
        return true;
    }
}
