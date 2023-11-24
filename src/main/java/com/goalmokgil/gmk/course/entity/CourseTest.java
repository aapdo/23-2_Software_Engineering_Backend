package com.goalmokgil.gmk.course.entity;

import com.goalmokgil.gmk.account.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document(collation = "course")
public class CourseTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    // 한 명의 유저가 여러 course를 가질 수 있음.
    @ManyToOne
    @NotNull
    @JoinColumn(name="userId")
    private Member member;

    @CreatedDate
    @NotNull
    private LocalDateTime createdDate;

    @LastModifiedDate
    @NotNull
    private LocalDateTime modifiedDate;

    private LocalDateTime deletedDate;

    public CourseTest(Long courseId, Member member, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.courseId = courseId;
        this.member = member;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
