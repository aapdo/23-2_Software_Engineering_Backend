package com.goalmokgil.gmk.account.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReqSignupDto {

    @NotBlank(message = "ID는 필수값 입니다")
    private String memberId;

    @NotBlank(message = "비밀번호는 필수값 입니다")
    private String password;

    @NotBlank(message = "이름은 필수값 입니다")
    private String name;

    @NotBlank(message = "닉네임은 필수값 입니다")
    private String nickname;

    @NotBlank(message = "생일는 필수값 입니다")
    @Size(min = 8, max = 8, message = "생년월일은 8자리 입니다")
    private String birth;

    @NotBlank(message = "이메일은 필수값 입니다")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;
}

