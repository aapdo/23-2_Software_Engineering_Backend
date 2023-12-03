package com.goalmokgil.gmk.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goalmokgil.gmk.post.entity.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class PostDto {
    private Long postId;
    private Long courseId;
    private Long userId;
    private String title;
    private String authorName;
    private String authorNickName;
    private String loginId;
    private String email;
    private List<PostContent> postData;
    //private PostData postData;

    private List<Long> likes;
    private List<String> tags;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date modifiedDate;

    public PostDto(Post post) {
        this.postId = post.getPostId();
        this.courseId = post.getRelatedCourse().getCourseId();
        this.userId = post.getMember().getUserId();
        this.authorName = post.getMember().getName();
        this.authorNickName = post.getMember().getNickname();
        this.email = post.getMember().getEmail();
        this.loginId = post.getMember().getLoginId();
        this.postData = post.getPostData();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
        this.title = post.getTitle();

        this.tags = new ArrayList<>();
        this.likes = new ArrayList<>();

        for (Tag tag : post.getTags()) {
            this.tags.add(tag.getName());
        }
        assert post.getLikes() != null;
        for (Likes likes : post.getLikes()) {
            this.likes.add(likes.getMember().getUserId());
        }

    }
}
