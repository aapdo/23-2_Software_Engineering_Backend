package com.goalmokgil.gmk.post.controller;

import com.goalmokgil.gmk.post.dto.PostDto;
import com.goalmokgil.gmk.post.entity.Post;
import com.goalmokgil.gmk.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

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
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto) {
        Post createdPost = postService.createPost(postDto);
        return ResponseEntity.ok(createdPost);
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody PostDto postDto) {
        Post updatedPost = postService.updatePost(postId, postDto);
        return ResponseEntity.ok(updatedPost);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }
}