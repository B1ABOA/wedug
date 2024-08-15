package com.b1aboa.wedug.jwt;

import com.b1aboa.wedug.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

        setFilterProcessesUrl("/api/auth/login");

    }
    // 로그인
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        log.info("ATTEMPT_AUTH_FILTER START");

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);
//        try {
//            response.sendRedirect("/");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        log.info("ATTEMPT_AUTH_FILTER FINISH");
        return authenticationManager.authenticate(authToken);
    }

    // 로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됩니다.)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        log.info("SUCCESS_AUTH START");
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String userId = customUserDetails.getUsername();

        // Role 은 Refactoring 때 진행
        String token = jwtUtil.createJwt(userId,60*60*10L);
        //RFC 7235 정의에 따른 인증 헤더 형태 -> Authorization: Bearer 인증토큰 string
        // ** 주의 Bearer 뒤에 공백 하나 있어야 함.
        // jwt 토큰 헤더에 추가하여 반환
        // response.addHeader("Authorization", "Bearer " + token);

        response.setContentType("application/json");
        response.getWriter().write("{\"token\":\"" + token + "\"}");
        response.getWriter().flush();

        log.info("SUCCESS_AUTH FINISH");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed){
        response.setStatus(401);
        log.info("UNSUCCESS_AUTH FINISH");
    }
}