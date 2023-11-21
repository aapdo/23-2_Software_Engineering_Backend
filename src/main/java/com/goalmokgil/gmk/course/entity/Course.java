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

    // 한 명의 유저가 여러 course를 가질 수 있음.
    @ManyToOne
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
