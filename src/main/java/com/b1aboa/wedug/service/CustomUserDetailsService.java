package com.b1aboa.wedug.service;

import com.b1aboa.wedug.dto.CustomUserDetails;
import com.b1aboa.wedug.entity.User;
import com.b1aboa.wedug.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    //DB에서 username(userId)를 통해 객체를 찾는다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUserId(username);
        log.info("LOADUSER_START");

        if(user != null){
            log.info("LOADUSER_FINISH");
            return new CustomUserDetails(user);
        }
        log.info("LOADUSER_FINISH");
        return null;
    }
}
