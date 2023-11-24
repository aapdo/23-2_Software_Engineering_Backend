package com.goalmokgil.gmk.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtLoginDto {

    private String grantType;
    private String accessToken;
    private Long userId;
}