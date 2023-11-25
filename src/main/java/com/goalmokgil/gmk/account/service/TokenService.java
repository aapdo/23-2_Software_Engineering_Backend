package com.goalmokgil.gmk.account.service;

import com.goalmokgil.gmk.config.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/*
 API 호출 시, Member의 정보가 헤더에 담겨져 올텐데, 어떤 Member가 API를 요청했는지 조회하는 코드이다.
 이제 SecurityUtil.getCurrentMemberId() 코드를 사용하면 편리하게 현재 memberId를 조회할 수 있다.
*/

@Service
@AllArgsConstructor
public class TokenService {
    private final JwtTokenProvider jwtTokenProvider;
    // 토큰에서 userId 추출
    public Long getCurrentUserId(String token) {
        Claims claims = jwtTokenProvider.extractClaims(token);
        return Long.parseLong(claims.get("userId").toString());
    }
}
