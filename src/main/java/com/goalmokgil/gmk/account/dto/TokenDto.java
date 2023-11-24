package com.goalmokgil.gmk.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private String grantType;
    private String accessToken;
    public TokenDto(JwtLoginDto jwtLoginDto){
        this.grantType = jwtLoginDto.getGrantType();
        this.accessToken = jwtLoginDto.getAccessToken();
    }
}