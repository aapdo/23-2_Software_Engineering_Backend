package com.goalmokgil.gmk.account.entity;
import com.goalmokgil.gmk.course.entity.Course;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String loginId;
    private String password;
    private String name;
    private String nickname;
    private String birth;
    private String email;

    @Builder
    public Member(String loginId, String password, String name, String nickname, String birth, String email) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.birth = birth;
        this.email = email;
    }


    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> course;

    // Post push하면 이것도 쓰면 됨
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Post> post;
}