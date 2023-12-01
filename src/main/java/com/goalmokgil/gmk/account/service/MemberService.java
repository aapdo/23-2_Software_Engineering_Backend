package com.goalmokgil.gmk.account.service;

import com.goalmokgil.gmk.account.dto.req.ReqMemberDto;
import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.course.dto.CourseDto;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.post.dto.PostDto;
import com.goalmokgil.gmk.post.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public ReqMemberDto getMemberDetails(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        ReqMemberDto reqMemberDto = new ReqMemberDto();
        reqMemberDto.setLoginId(member.getLoginId());
        reqMemberDto.setName(member.getName());
        reqMemberDto.setNickname(member.getNickname());
        reqMemberDto.setBirth(member.getBirth());
        reqMemberDto.setEmail(member.getEmail());
//        reqMemberDto.setCourses();
//        reqMemberDto.setPosts();


        return reqMemberDto;
    }
}
