package com.goalmokgil.gmk.account.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "courses")
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


    // mappedBy 둘다 삭제. 1:N 에서 N쪽만 mappedBy 씀
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore // 참조 무한재귀 방지용
    private List<Course> courses;

    @OneToMany( cascade = CascadeType.ALL) // author가 member랑 똑같음
    @JsonIgnore
    private List<Post> posts;

    public void addCourse(Course course) {
        this.courses.add(course);
    }
}