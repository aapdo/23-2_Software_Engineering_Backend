package com.goalmokgil.gmk.account.service;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;
// 주의!! passwordencoder를 한 상태로 UserDetail을 만든다면 암호화된 그 자체가 비밀번호가 되어버린다. 즉 암호화가 2번 되어버림 (?)

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByMemberId(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member) {
        return User.builder()
                .username(member.getMemberId())
                .password(member.getPassword())
                .build();
    }
}