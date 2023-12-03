package com.goalmokgil.gmk.post.controller;

import com.goalmokgil.gmk.post.dto.req.ReqPostDto;
import com.goalmokgil.gmk.post.dto.res.ResPostDto;
import com.goalmokgil.gmk.post.entity.Post;
import com.goalmokgil.gmk.post.service.LikeService;
import com.goalmokgil.gmk.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final LikeService likeService;

    // 특정 게시글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long postId) {
        Post post = postService.getPost(postId);
        return ResponseEntity.ok(post);
    }

    // 모든 게시글 조회
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 게시글 만들기
    @PostMapping
    public ResponseEntity<ResPostDto> createPost(@RequestBody ReqPostDto reqPostDto) {
        ResPostDto resPostDto = postService.createPost(reqPostDto);
        return ResponseEntity.ok(resPostDto);
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody ReqPostDto reqPostDto) {
        Post updatedPost = postService.updatePost(postId, reqPostDto);
        return ResponseEntity.ok(updatedPost);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }


    // 특정 포스트의 '좋아요' 개수를 반환하는 엔드포인트
    @GetMapping("/{postId}/likes/count")
    public ResponseEntity<?> countLikes(@PathVariable Long postId) {
        long likeCount = likeService.countLikesByPost(postId);
        return ResponseEntity.ok(Collections.singletonMap("likeCount", likeCount));
    }
}