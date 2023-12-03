package com.goalmokgil.gmk.post.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.post.dto.PostDto;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.Nullable;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "userId", unique = false)
    private Member member;

    // 동시성 문제로 Course => courseId로 수정
    private Long courseId;

    @NotNull
    private String title;

    @OneToMany
    @JsonManagedReference
    @Nullable
    private List<Likes> likes;
    
    // 태그 추가
    @ManyToMany
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonManagedReference
    private List<Tag> tags = new ArrayList<>();




    @Type(JsonType.class)
    @NotNull
    @Column(name = "postData", columnDefinition = "TEXT")
    //private PostData postData;
    private List<PostContent> postData;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date createdDate;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date modifiedDate;

    public Post(PostDto postDto, Long courseId, Member member, List<Tag> tags) {
        this.courseId = courseId;
        this.member = member;
        this.title = postDto.getTitle();
        this.postData = postDto.getPostData();
        this.tags = tags;
        //this.likes = likes;
        this.likes = new ArrayList<>();
        this.createdDate = postDto.getCreatedDate();
        this.modifiedDate = postDto.getModifiedDate();
    }
    public Post(PostDto postDto, Long courseId, Member member, List<Tag> tags, List<Likes> likes) {
        this.courseId = courseId;
        this.member = member;
        this.title = postDto.getTitle();
        this.postData = postDto.getPostData();
        this.tags = tags;
        this.likes = likes;
        this.likes = new ArrayList<>();
        this.createdDate = postDto.getCreatedDate();
        this.modifiedDate = postDto.getModifiedDate();
    }
    public Post(Post post, PostDto postDto){
        this.postId = post.getPostId();
        this.member = post.getMember();
        this.courseId = post.getCourseId();
        this.title = postDto.getTitle();
        this.postData = postDto.getPostData();
        this.tags = post.getTags();
        this.likes = post.getLikes();
        this.modifiedDate = postDto.getModifiedDate();
        this.createdDate = postDto.getCreatedDate();

    }
}
