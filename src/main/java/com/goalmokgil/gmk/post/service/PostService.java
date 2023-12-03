package com.goalmokgil.gmk.post.service;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.repository.CourseRepository;
import com.goalmokgil.gmk.post.dto.req.ReqPostDto;
import com.goalmokgil.gmk.post.dto.res.ResPostDto;
import com.goalmokgil.gmk.post.entity.Post;
import com.goalmokgil.gmk.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

    // Post 조회
    @Transactional(readOnly = true)
    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    // 모든 Post 조회
    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }


    // Post 생성
    public ResPostDto createPost(ReqPostDto reqPostDto) {
        // 사용자 정보 조회
        Member member = memberRepository.findById(reqPostDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 코스 정보 조회 및 설정
        List<Course> courses = new ArrayList<>(); // List 초기화
        for (Long courseId : reqPostDto.getCourseIds()) {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            courses.add(course);
        }

        // 새로운 Post 객체 생성 및 저장
        Post post = new Post();
        post.setAuthor(member);
        post.setRelatedCourses(courses); // 관련 코스 설정
        post.setTitle(reqPostDto.getTitle());
        post.setContent(reqPostDto.getContent());
        postRepository.save(post);


        ResPostDto resPostDto = new ResPostDto();
        resPostDto.setPostId(post.getPostId());
        resPostDto.setAuthorName(post.getAuthor().getName());
        resPostDto.setAuthorNickname(post.getAuthor().getNickname());
        resPostDto.setCourseList(post.getRelatedCourses());

        resPostDto.setTitle(post.getTitle());
        resPostDto.setContent(post.getContent());
        resPostDto.setCreatedDate(post.getCreatedDate());
        resPostDto.setModifiedDate(post.getModifiedDate());

        return resPostDto;
    }


    // 게시글 수정하기
    public Post updatePost(Long postId, ReqPostDto reqPostDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setTitle(reqPostDto.getTitle());
        post.setContent(reqPostDto.getContent());
        // 필요한 경우 다른 필드도 업데이트

        return postRepository.save(post);
    }

    // 게시글 삭제하기
    public void deletePost(Long postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.deleteById(postId);
    }
}