package com.goalmokgil.gmk.account.service;

import com.goalmokgil.gmk.account.dto.req.ReqMemberDto;
import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.course.dto.CourseDto;
import com.goalmokgil.gmk.course.service.CourseService;
import com.goalmokgil.gmk.post.dto.PostDto;
import com.goalmokgil.gmk.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final CourseService courseService;
    private final PostService postService;

    public ReqMemberDto getMemberDetails(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        List<CourseDto> courses = courseService.getAllCourseByMemberId(userId);
        List<PostDto> posts = postService.getPostsByUser(userId);

        return new ReqMemberDto(
                member.getLoginId(),
                member.getName(),
                member.getNickname(),
                member.getBirth(),
                member.getEmail(),
                courses,
                posts
        );
    }
}
