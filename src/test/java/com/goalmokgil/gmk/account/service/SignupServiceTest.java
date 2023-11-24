package com.goalmokgil.gmk.account.service;

import com.goalmokgil.gmk.account.dto.req.ReqSignupDto;
import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SignupServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private SignupService signupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void memberSignup() {
        // Given
        ReqSignupDto reqSignupDto = new ReqSignupDto("testId", "password", "Test Name", "TestNick", "1990-01-01", "test@example.com");
        Member member = Member.builder()
                .memberId(reqSignupDto.getMemberId())
                .password(passwordEncoder.encode(reqSignupDto.getPassword()))
                .name(reqSignupDto.getName())
                .nickname(reqSignupDto.getNickname())
                .birth(reqSignupDto.getBirth())
                .email(reqSignupDto.getEmail())
                .build();

        when(memberRepository.save(any(Member.class))).thenReturn(member);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // When
        Member result = signupService.memberSignup(reqSignupDto);

        // Then
        assertNotNull(result);
        assertEquals(reqSignupDto.getMemberId(), result.getMemberId());
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    void testIsIdDuplicate() {
        // Given
        String memberId = "testId";
        when(memberRepository.existsByLoginId(memberId)).thenReturn(true);

        // When
        boolean isDuplicate = signupService.isIdDuplicate(memberId);

        // Then
        assertTrue(isDuplicate);
        verify(memberRepository, times(1)).existsByLoginId(memberId);
    }

    @Test
    void testIsNicknameDuplicate() {
        // Given
        String nickname = "TestNick";
        when(memberRepository.existsByNickname(nickname)).thenReturn(true);

        // When
        boolean isDuplicate = signupService.isNicknameDuplicate(nickname);

        // Then
        assertTrue(isDuplicate);
        verify(memberRepository, times(1)).existsByNickname(nickname);
    }
}