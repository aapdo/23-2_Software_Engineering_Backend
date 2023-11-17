package com.goalmokgil.gmk.account.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String memberId;
    private String password;
    private String name;
    private String nickname;
    private String birth;
    private String email;

    @Builder
    public Member(String memberId, String password, String name, String nickname, String birth, String email) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.birth = birth;
        this.email = email;
    }
}