package com.goalmokgil.gmk.account.service;

import com.goalmokgil.gmk.account.dto.req.ReqSignupDto;
import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.course.entity.Course;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class SignupService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member memberSignup(ReqSignupDto reqSignupDto) {

        if (isIdDuplicate(reqSignupDto.getLoginId())) {
            throw new RuntimeException("중복된 ID입니다.");
        }

        // 닉네임 중복 체크
        if (isNicknameDuplicate(reqSignupDto.getNickname())) {
            throw new RuntimeException("중복된 닉네임입니다.");
        }

        Member member = Member.builder()
                .loginId(reqSignupDto.getLoginId())
                .password(passwordEncoder.encode(reqSignupDto.getPassword()))
                .name(reqSignupDto.getName())
                .nickname(reqSignupDto.getNickname())
                .birth(reqSignupDto.getBirth())
                .email(reqSignupDto.getEmail())
                .courses(new ArrayList<Course>())
                .build();
        memberRepository.save(member);
        return member;
    }

    public boolean isIdDuplicate(String memberId)
    {
        // true  : 중복이다
        // false : 중복 아니다, 즉 false이어야 통과
        return memberRepository.existsByLoginId(memberId);
    }

    public boolean isNicknameDuplicate(String nickname)
    {
        return memberRepository.existsByNickname(nickname);
    }
}