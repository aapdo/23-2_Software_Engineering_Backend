package com.goalmokgil.gmk.post.dto.req;

import com.goalmokgil.gmk.post.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ReqPostDto {

    private Long memberId; // 게시글을 작성한 사용자의 ID
    private Long courseId; // 관련 코스의 ID
    // private String title; // 게시글 제목
    // private String content; // 게시글 내용
    private Set<String> tags; // 태그 정보

//    private CourseData courseData;
}
