package com.goalmokgil.gmk.post.service;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.repository.CourseRepository;
import com.goalmokgil.gmk.post.dto.PostDto;
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
    public Post createPost(PostDto postDto) {
        // 사용자 정보 조회
        Member member = memberRepository.findById(postDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 코스 정보 조회 및 설정
        Course course = courseRepository.findById(postDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // 새로운 Post 객체 생성 및 저장
        Post post = new Post(postDto, course, member);

        return postRepository.save(post);
    }


    public Post updatePost(Long postId, PostDto postDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Post newPost = new Post(post, postDto);

        return postRepository.save(newPost);
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