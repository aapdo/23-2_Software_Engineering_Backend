package com.goalmokgil.gmk.account.controller;

import com.goalmokgil.gmk.account.dto.req.ReqSignupDto;
import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.service.SignupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

import static com.goalmokgil.gmk.response.ResBody.successResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SignupController {

    private final SignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerMember(@RequestBody ReqSignupDto reqSignupDto) throws IOException {

        Member member = signupService.memberSignup(reqSignupDto);
        return successResponse(reqSignupDto);
    }
}