package com.goalmokgil.gmk.post.service;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.repository.CourseRepository;
import com.goalmokgil.gmk.post.dto.PostDto;
import com.goalmokgil.gmk.post.entity.Post;
import com.goalmokgil.gmk.post.entity.Tag;
import com.goalmokgil.gmk.post.repository.PostRepository;
import com.goalmokgil.gmk.post.repository.TagRepository;
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


        log.info("new post: "+ newPost);

        return postRepository.save(newPost);
    }


    @Transactional
    public Post updatePost(Long postId, PostDto postDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        log.info("postDto title:" + postDto.getTitle());
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


    // 포스트 좋아요 순으로 정렬
    @Transactional(readOnly = true)
    public List<Post> getAllPostsSortedByLikes() {
        return postRepository.findAllPostsOrderByLikes();
    }
}