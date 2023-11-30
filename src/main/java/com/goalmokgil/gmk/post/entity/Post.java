package com.goalmokgil.gmk.post.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.course.entity.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Member author;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course relatedCourse;

    @NotNull
    private String title;
    @NotNull
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date createdDate;


    public Post(Member author, Course relatedCourse, String title, String content, Date createdDate) {
        this.author = author;
        this.relatedCourse = relatedCourse;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}
