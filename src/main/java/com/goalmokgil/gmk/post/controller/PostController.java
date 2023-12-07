package com.goalmokgil.gmk.post.controller;

import com.goalmokgil.gmk.account.service.TokenService;
import com.goalmokgil.gmk.post.dto.PostDto;
import com.goalmokgil.gmk.post.dto.req.ReqPostDto;
import com.goalmokgil.gmk.post.dto.res.ResPostDto;
import com.goalmokgil.gmk.post.entity.Post;
import com.goalmokgil.gmk.post.service.LikeService;
import com.goalmokgil.gmk.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final LikeService likeService;
    private final TokenService tokenService;

    // 특정 게시글 조회
    // Post => PostDto
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId) {
        Post post = postService.getPost(postId);
        log.info("get post: " + post);
        return ResponseEntity.ok(new PostDto(post));
    }

    // 모든 게시글 조회
    // List<Post> => List<PostDto>
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            postDtos.add(new PostDto(post));
        }
        return ResponseEntity.ok(postDtos);
    }

    // 내 포스트 보기
    @GetMapping("/myPosts")
    public ResponseEntity<List<PostDto>> getMyPosts(@RequestHeader("Authorization") String authorizationHeader) {
        Long userId = tokenService.getCurrentUserIdByAuthorizationHeader(authorizationHeader);
        List<PostDto> posts = postService.getPostsByUser(userId);
        return ResponseEntity.ok(posts);
    }

    // 게시글 만들기
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestHeader("Authorization") String authorizationHeader, @RequestBody PostDto postDto) {
        Long userId = tokenService.getCurrentUserIdByAuthorizationHeader(authorizationHeader);
        return ResponseEntity.ok(new PostDto(postService.createPost(userId, postDto)));
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long postId, @RequestBody PostDto postDto) {
        Long userId = tokenService.getCurrentUserIdByAuthorizationHeader(authorizationHeader);
        return ResponseEntity.ok(new PostDto(postService.updatePost(userId, postId, postDto)));
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long postId) {
        Long userId = tokenService.getCurrentUserIdByAuthorizationHeader(authorizationHeader);
        postService.deletePost(userId, postId);
        return ResponseEntity.ok().build();
    }


    // 포스트의 좋아요 개수 반환
    @GetMapping("/{postId}/likes/count")
    public ResponseEntity<?> countLikes(@PathVariable Long postId) {
        long likeCount = likeService.countLikesByPost(postId);
        return ResponseEntity.ok(Collections.singletonMap("likeCount", likeCount));
    }

    // 태그로 포스트 검색하기
    // List<Post> => List<PostDto>
    @GetMapping("/by-tag/{tagName}")
    public ResponseEntity<List<PostDto>> getPostsByTag(@PathVariable String tagName) {
        List<Post> posts = postService.findPostsByTagName(tagName);
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            postDtos.add(new PostDto(post));
        }
        return ResponseEntity.ok(postDtos);
    }


    // 좋아요 순으로 포스트 정렬
    @GetMapping("/orderBy-likes")
    public ResponseEntity<List<Post>> getPostsSortedByLikes() {
        List<Post> posts = postService.getPostsOrderByLikes();
        return ResponseEntity.ok(posts);
    }
}