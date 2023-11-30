package com.goalmokgil.gmk.post.dto;

import com.goalmokgil.gmk.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Long postId;
    //private Long memberId; // 게시글을 작성한 사용자의 ID
    //private Long courseId; // 관련 코스의 ID
    private String title;
    private String content;

    // DTO에서 Entity로 변환
    public Post toEntity() {
        Post post = new Post();
        post.setPostId(this.postId);
        post.setTitle(this.title);
        post.setContent(this.content);
        // Member와 Course 설정은 Service 레벨에서 처리
        return post;
    }
}
