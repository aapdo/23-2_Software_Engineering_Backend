package com.goalmokgil.gmk.post.dto.res;

import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.post.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class ResPostDto {

    private Long postId;
    private String authorName;
    private String authorNickname;
    private List<Course> courseList;

    private String title;
    private String content;
    private Date createdDate;
    private Date modifiedDate;

    private Set<Tag> tags; // 태그 정보 추가

}
