package com.b1aboa.wedug.service;

import com.b1aboa.wedug.dto.UserDTO;
import com.b1aboa.wedug.entity.NationInfo;
import com.b1aboa.wedug.entity.User;
import com.b1aboa.wedug.repository.NationInfoRepository;
import com.b1aboa.wedug.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
public class UserServiceImpl implements  UserService{
    private final UserRepository userRepository;
    private final NationInfoRepository nationInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    public boolean createUser(UserDTO userDTO) {
        if (userDTO == null) return false;

        User user = new User();
        NationInfo nationInfo = new NationInfo();

        if(userRepository.existsByUserId(userDTO.getUserId())){
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
    @Override
    public UserDTO getUserInfo(String userId) {
        User user = userRepository.findByUserId(userId);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void updateUser(UserDTO userDto) {
        User existingUser = userRepository.findByUserId(userDto.getUserId());
        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }

        existingUser.setNickname(userDto.getNickname());
        existingUser.setGender(userDto.getGender());
        existingUser.setBirthYear(userDto.getBirthYear());
//
//        if (userDto.getNationCode() != null) {
//            NationInfo nationInfo = nationInfoRepository.getReferenceById(userDto.getNationCode());
//            existingUser.setNationCode(nationInfo);
//        }

        // 비밀번호 변경이 필요한 경우 (옵션)
        // if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
        //     existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        // }

        userRepository.save(existingUser);
    }



}
