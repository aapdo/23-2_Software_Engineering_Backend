package com.goalmokgil.gmk.post.service;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.repository.CourseRepository;
import com.goalmokgil.gmk.post.dto.PostDto;
import com.goalmokgil.gmk.post.entity.Post;
import com.goalmokgil.gmk.post.repository.PostRepository;
import com.goalmokgil.gmk.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Post createPost(PostDto postDto) {
        // 사용자 정보 조회
        Member member = memberRepository.findById(postDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 코스 정보 조회 및 설정
        List<Course> courses = new ArrayList<>(); // List 초기화
        for (Long courseId : postDto.getCourseIds()) {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            courses.add(course);
        }

        // 새로운 Post 객체 생성 및 저장
        Post post = new Post();
        post.setAuthor(member);
        post.setRelatedCourses(courses); // 관련 코스 설정
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        return postRepository.save(post);
    }


    // 게시글 수정하기
    public Post updatePost(Long postId, PostDto postDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
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