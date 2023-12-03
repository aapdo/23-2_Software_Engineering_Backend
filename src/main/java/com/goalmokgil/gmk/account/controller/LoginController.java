package com.goalmokgil.gmk.account.controller;

import com.goalmokgil.gmk.account.dto.JwtLoginDto;
import com.goalmokgil.gmk.account.dto.req.ReqLoginDto;
import com.goalmokgil.gmk.account.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public JwtLoginDto login(@RequestBody ReqLoginDto reqLoginDto) {
        String memberId = reqLoginDto.getLoginId();
        String password = reqLoginDto.getPassword();

        JwtLoginDto jwtLoginDto = JwtLoginDto.builder()
                .grantType(loginService.login(memberId, password).getGrantType())
                .accessToken(loginService.login(memberId, password).getAccessToken())
                .loginId(reqLoginDto.getLoginId())
                .build();

        return jwtLoginDto;
    }
}