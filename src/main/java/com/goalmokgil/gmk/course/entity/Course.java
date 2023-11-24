package com.goalmokgil.gmk.course.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goalmokgil.gmk.account.entity.Member;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    // 연관관계의 주인(여기서는 member)은 mappedBy 속성이 없다. 주인이 아닌쪽에 주인을 지정해주는 것.
    // 연관관계의 주인이 외래키를 관리(등록 수정 삭제)를 할 수 있음.
    // 주인이 아닌 쪽은 읽기만 할 수 있다.
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // @ManyToOne은 항상 연관관계의 주인이 됨. mappedBy 속성이 없음.
    @NotNull
    @JoinColumn(name = "userId")
    private Member member;

    @Type(JsonType.class)
    @Column(name = "courseData", columnDefinition = "longtext")
    @NotNull
    private CourseData courseData;

    @CreatedDate
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a")
    private Date createdDate;

    @LastModifiedDate
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a")
    private Date modifiedDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a")
    private Date deletedDate;


    public Course(Long courseId, Member member, CourseData courseData, Date createdDate, Date modifiedDate) {
        this.courseId = courseId;
        this.member = member;
        this.courseData = courseData;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
