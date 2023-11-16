package com.goalmokgil.gmk.account.service;

import com.goalmokgil.gmk.account.dto.req.ReqSignupDto;
import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class SignupService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member memberSignup(ReqSignupDto reqSignupDto) {
        Member member = Member.builder()
                .memberId(reqSignupDto.getMemberId())
                .password(passwordEncoder.encode(reqSignupDto.getPassword()))
                .name(reqSignupDto.getName())
                .nickname(reqSignupDto.getNickname())
                .birth(reqSignupDto.getBirth())
                .email(reqSignupDto.getEmail())
                .build();
        memberRepository.save(member);
        return member;
    }
}