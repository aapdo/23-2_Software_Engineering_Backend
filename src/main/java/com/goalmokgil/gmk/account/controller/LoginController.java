package com.goalmokgil.gmk.account.controller;

import com.goalmokgil.gmk.account.dto.JwtLoginDto;
import com.goalmokgil.gmk.account.dto.TokenDto;
import com.goalmokgil.gmk.account.dto.req.ReqLoginDto;
import com.goalmokgil.gmk.account.service.LoginService;
import com.goalmokgil.gmk.account.service.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final TokenService tokenService;

    @PostMapping("/login")

    public ResponseEntity<JwtLoginDto> login(@RequestBody ReqLoginDto reqLoginDto, HttpServletResponse response) {
        String memberId = reqLoginDto.getLoginId();
        String password = reqLoginDto.getPassword();
        TokenDto loginToken = loginService.login(memberId, password);


        JwtLoginDto jwtLoginDto = JwtLoginDto.builder()
                .grantType(loginToken.getGrantType())
                .accessToken(loginToken.getAccessToken())
                .loginId(reqLoginDto.getLoginId())
                .userId(tokenService.getCurrentUserId(loginToken.getAccessToken()))
                .build();

        HttpHeaders header = new HttpHeaders();
        Cookie loginCookie = new Cookie("loginToken", jwtLoginDto.getAccessToken());

        loginCookie.setPath("/");
        // 30일 간 쿠키 유지.
        loginCookie.setMaxAge(60 * 60 * 24 * 30);

        header.add("Set-Cookie", loginCookie.toString());
        header.add("Authorization", "Bearer "+jwtLoginDto.getAccessToken());
        response.addCookie(loginCookie);

        return ResponseEntity.ok()
                .headers(header)
                .body(jwtLoginDto);
    }
}