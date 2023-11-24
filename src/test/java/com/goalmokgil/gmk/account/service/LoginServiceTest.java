package com.goalmokgil.gmk.account.service;

import com.goalmokgil.gmk.account.dto.TokenDto;
import com.goalmokgil.gmk.config.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void loginSuccessTest() {
        // Given
        String memberId = "user";
        String password = "password";
        UserDetails userDetails = new User(memberId, password, Collections.emptyList());
        Authentication authentication = mock(Authentication.class);
        TokenDto expectedTokenDto = new TokenDto("token", "refreshToken");

        when(userDetailsService.loadUserByUsername(memberId)).thenReturn(userDetails);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtTokenProvider.generateToken(authentication)).thenReturn(expectedTokenDto);

        // When
        TokenDto resultTokenDto = loginService.login(memberId, password);

        // Then
        assertNotNull(resultTokenDto);
        assertEquals(expectedTokenDto.getAccessToken(), resultTokenDto.getAccessToken());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtTokenProvider).generateToken(authentication);
    }

    @Test
    void loginFailureTest() {
        // Given
        String memberId = "user";
        String password = "wrongpassword";
        when(userDetailsService.loadUserByUsername(memberId)).thenThrow(new UsernameNotFoundException("User not found"));

        // When & Then
        assertThrows(UsernameNotFoundException.class, () -> loginService.login(memberId, password));
        verify(authenticationManager, never()).authenticate(any(Authentication.class));
        verify(jwtTokenProvider, never()).generateToken(any(Authentication.class));
    }
}
