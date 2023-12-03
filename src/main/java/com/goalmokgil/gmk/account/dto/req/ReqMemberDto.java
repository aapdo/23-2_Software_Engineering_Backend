package com.goalmokgil.gmk.account.dto.req;


import com.goalmokgil.gmk.course.dto.CourseDto;
import com.goalmokgil.gmk.post.dto.req.ReqPostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqMemberDto {
    private String loginId;
    private String name;
    private String nickname;
    private String birth;
    private String email;
    private List<CourseDto> courses;
    private List<ReqPostDto> posts;
}
