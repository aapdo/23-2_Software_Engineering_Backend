package com.goalmokgil.gmk.post.service;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.repository.CourseRepository;
import com.goalmokgil.gmk.exception.EntityNotFoundException;
import com.goalmokgil.gmk.exception.ForbiddenException;
import com.goalmokgil.gmk.post.dto.PostDto;
import com.goalmokgil.gmk.post.entity.Post;
import com.goalmokgil.gmk.post.entity.Tag;
import com.goalmokgil.gmk.post.repository.LikesRepository;
import com.goalmokgil.gmk.post.repository.PostRepository;
import com.goalmokgil.gmk.post.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;

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


    // 내 포스트 보기
    public List<PostDto> getPostsByUser(Long userId) {
        List<Post> posts = postRepository.findByMemberUserId(userId);
        return posts.stream()
                .map(PostDto::new)
                .collect(Collectors.toList());
    }


    // Post 생성
    @Transactional
    public Post createPost(Long userId, PostDto postDto) {
        // 사용자 정보 조회
        log.info("start create post");
        log.info("postDto: " + postDto);
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 코스 정보 조회 및 설정
        Course course = courseRepository.findById(postDto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        List<Tag> tags = new ArrayList<>();

        for (String tagName : postDto.getTags()) {
            tags.add(tagRepository.save(new Tag(tagName)));
        }


        // 새로운 Post 객체 생성 및 저장
        Post newPost = new Post(postDto, course.getCourseId(), member, tags);
        member.getPosts().add(newPost);

        log.info("new post: "+ newPost);

        return postRepository.save(newPost);
    }


    @Transactional
    public Post updatePost(Long userId, Long postId, PostDto postDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Member member = memberRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("잘못된 계정 정보입니다."));

        if (member.getUserId().equals(post.getMember().getUserId())) {
            log.info("postDto title:" + postDto.getTitle());
            Post newPost = new Post(post, postDto);
            return postRepository.save(newPost);
        } else {
            throw new ForbiddenException("해당 포스트에 대한 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
    }

    // 게시글 삭제하기
    public void deletePost(Long userId, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Member member = memberRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("잘못된 계정 정보입니다."));

        if (member.getUserId().equals(post.getMember().getUserId())) {
            postRepository.deleteById(postId);
        } else {
            throw new ForbiddenException("해당 포스트에 대한 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
    }


    // 태그로 포스트 찾기
    public List<Post> findPostsByTagName(String tagName) {
        return postRepository.findByTags_Name(tagName);
    }


    // 포스트 좋아요 순으로 정렬
    public List<Post> getPostsOrderByLikes() {
        return postRepository.findAllOrderByLikesDesc();
    }
}