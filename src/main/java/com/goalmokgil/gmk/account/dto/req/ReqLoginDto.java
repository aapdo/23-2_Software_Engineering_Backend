package com.goalmokgil.gmk.account.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqLoginDto {
    private String memberId;
    private String password;
}