package com.goalmokgil.gmk.account.controller;

import com.goalmokgil.gmk.account.dto.JwtLoginDto;
import com.goalmokgil.gmk.account.dto.req.ReqLoginDto;
import com.goalmokgil.gmk.account.service.LoginService;
import com.goalmokgil.gmk.account.service.TokenService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")

    public ResponseEntity<JwtLoginDto> login(@RequestBody ReqLoginDto reqLoginDto) {
        String memberId = reqLoginDto.getLoginId();
        String password = reqLoginDto.getPassword();



        JwtLoginDto jwtLoginDto = JwtLoginDto.builder()
                .grantType(loginService.login(memberId, password).getGrantType())
                .accessToken(loginService.login(memberId, password).getAccessToken())
                .loginId(reqLoginDto.getLoginId())
                .build();

        HttpHeaders header = new HttpHeaders();
        Cookie loginCookie = new Cookie("loginToken", jwtLoginDto.getAccessToken());

        loginCookie.setPath("/");
        // 30일 간 쿠키 유지.
        loginCookie.setMaxAge(60 * 60 * 24 * 30);

        header.add("Set-Cookie", loginCookie.toString());

        return ResponseEntity.ok()
                .headers(header)
                .body(jwtLoginDto);
    }
}