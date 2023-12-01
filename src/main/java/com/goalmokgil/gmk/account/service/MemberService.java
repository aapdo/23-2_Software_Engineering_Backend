package com.goalmokgil.gmk.account.service;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member getMemberDetails(Long userId) {
        return memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }
}
