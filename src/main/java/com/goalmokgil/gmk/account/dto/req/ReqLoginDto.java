package com.goalmokgil.gmk.account.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReqLoginDto {
    private String memberId;
    private String password;
}