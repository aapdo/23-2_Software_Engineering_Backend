package com.goalmokgil.gmk.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TokenDto {

    private String grantType;
    private String accessToken;
}