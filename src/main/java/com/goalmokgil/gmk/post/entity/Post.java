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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    @OneToMany(mappedBy = "post")
    private Set<Course> relatedCourses = new HashSet<>();
    @NotNull
    private String title;
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String content;
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date createdDate;

    public Post(Member author, Set<Course> relatedCourses, String title, String content, Date createdDate) {
        this.author = author;
        this.relatedCourses = relatedCourses;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}
