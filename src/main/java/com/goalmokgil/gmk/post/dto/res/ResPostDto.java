package com.goalmokgil.gmk.post.dto.res;

import com.goalmokgil.gmk.course.entity.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


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
}
