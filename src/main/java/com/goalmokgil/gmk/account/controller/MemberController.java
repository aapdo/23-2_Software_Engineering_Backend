package com.goalmokgil.gmk.account.controller;


import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/member/{userId}")
    public ResponseEntity<Member> getMemberDetails(@PathVariable Long userId) {
        Member member = memberService.getMemberDetails(userId);
        return ResponseEntity.ok(member);
    }
}
