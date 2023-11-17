package com.goalmokgil.gmk.account.controller;

import com.goalmokgil.gmk.account.dto.req.ReqLoginDto;
import com.goalmokgil.gmk.account.dto.TokenDto;
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
    public TokenDto login(@RequestBody ReqLoginDto reqLoginDto) {
        TokenDto tokenDto = loginService.login(reqLoginDto.getMemberId(), reqLoginDto.getPassword());
        return tokenDto;
    }
}