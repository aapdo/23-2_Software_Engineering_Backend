package com.goalmokgil.gmk.account.controller;


import com.goalmokgil.gmk.account.dto.req.ReqMemberDto;
import com.goalmokgil.gmk.account.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/member/{userId}")
    public ResponseEntity<ReqMemberDto> getMemberDetails(@PathVariable Long userId) {
        ReqMemberDto reqMemberDto = memberService.getMemberDetails(userId);
        return ResponseEntity.ok(reqMemberDto);
    }
}
