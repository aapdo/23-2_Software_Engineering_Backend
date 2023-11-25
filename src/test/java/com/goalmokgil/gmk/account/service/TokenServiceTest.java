package com.goalmokgil.gmk.account.service;

import com.goalmokgil.gmk.config.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class TokenServiceTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @InjectMocks
    TokenService tokenService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testGetCurrentUserId() {
        // 가상의 토큰과 userId 값을 설정합니다.
        String token = "your_fake_token";
        Long userId = 123L;

        // Mockito를 사용하여 jwtTokenProvider.extractClaims()가 호출될 때 반환할 가상의 Claims를 만듦
        Claims mockClaims = Mockito.mock(Claims.class);
        Mockito.when(jwtTokenProvider.extractClaims(token)).thenReturn(mockClaims);
        Mockito.when(mockClaims.get("userId")).thenReturn(userId.toString());

        // getCurrentUserId 메서드를 호출하고 결과를 확인
        Long resultUserId = tokenService.getCurrentUserId(token);

        // 결과가 예상한 대로인지 확인
        assertEquals(userId, resultUserId);
    }
}