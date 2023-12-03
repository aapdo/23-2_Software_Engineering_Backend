package com.goalmokgil.gmk.post.dto;

import com.goalmokgil.gmk.post.entity.Likes;
import com.goalmokgil.gmk.post.entity.Post;
import com.goalmokgil.gmk.post.entity.PostData;
import com.goalmokgil.gmk.post.entity.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PostDto {
    private Long postId;
    private Long courseId;
    private Long userId;
    private String authorName;
    private String authorNickName;
    private String loginId;
    private String email;
    private PostData postData;

    private String title;

    private List<Likes> likes;
    private Set<Tag> tags;

    public PostDto(Long postId, Long courseId, Long userId, String authorName, String authorNickName, String loginId, String email, PostData postData) {
        this.postId = postId;
        this.courseId = courseId;
        this.userId = userId;
        this.authorName = authorName;
        this.authorNickName = authorNickName;
        this.loginId = loginId;
        this.email = email;
        this.postData = postData;
    }

    public PostDto(Post post) {

    }
}
