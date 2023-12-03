package com.goalmokgil.gmk.post.service;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.repository.CourseRepository;
import com.goalmokgil.gmk.post.dto.req.ReqPostDto;
import com.goalmokgil.gmk.post.dto.res.ResPostDto;
import com.goalmokgil.gmk.post.entity.Post;
import com.goalmokgil.gmk.post.entity.Tag;
import com.goalmokgil.gmk.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;
    private final TagService tagService;

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

        // 태그 정보 설정
        Set<Tag> tags = reqPostDto.getTags().stream()
                .map(tagName -> tagService.createOrGetTag(tagName))
                .collect(Collectors.toSet());
        post.setTags(tags);
        postRepository.save(post);


        ResPostDto resPostDto = new ResPostDto();
        resPostDto.setPostId(post.getPostId()); // 포스트 고유번호
        resPostDto.setAuthorName(post.getAuthor().getName()); // 만든 사람 이름
        resPostDto.setAuthorNickname(post.getAuthor().getNickname()); // 만든 사람 닉네임
        resPostDto.setCourseList(post.getRelatedCourses()); // 포스트 내 코스들

        resPostDto.setTitle(post.getTitle()); // 제목
        resPostDto.setContent(post.getContent()); // 내용
        resPostDto.setCreatedDate(post.getCreatedDate()); // 만든날짜
        resPostDto.setModifiedDate(post.getModifiedDate()); // 수정시간
        resPostDto.setTags(post.getTags()); // 태그

        return resPostDto;
    }


    // 게시글 수정하기 - 미완성 미완성 12.03.
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


    // 태그로 포스트 찾기
    public List<Post> findPostsByTagName(String tagName) {
        return postRepository.findByTags_Name(tagName);
    }
}