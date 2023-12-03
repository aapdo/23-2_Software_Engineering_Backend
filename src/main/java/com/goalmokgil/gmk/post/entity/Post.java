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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "relatedCourses")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private Member author;
    @OneToMany // (mappedBy = "post") 삭제
    private List<Course> relatedCourses;
    @OneToMany
    private List<Likes> likes;



    @NotNull
    private String title;
    @NotNull
    @Column(columnDefinition = "TEXT") // 내용을 늘려줌
    private String content;
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date createdDate;
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date modifiedDate;

    public Post(Member author, List<Course> relatedCourses, List<Likes> likes, String title, String content, Date createdDate, Date modifiedDate) {
        this.author = author;
        this.relatedCourses = relatedCourses;
        this.likes = likes;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
