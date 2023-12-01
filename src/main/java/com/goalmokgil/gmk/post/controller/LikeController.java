package com.goalmokgil.gmk.post.controller;

import com.goalmokgil.gmk.post.dto.LikeDto;
import com.goalmokgil.gmk.post.entity.Likes;
import com.goalmokgil.gmk.post.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<?> addLike(@RequestBody LikeDto likeDto) {
        boolean isLikedNow = likeService.addLike(likeDto.getUserId(), likeDto.getPostId());

        Map<String, Object> response = new HashMap<>();
        response.put("userId", likeDto.getUserId());
        response.put("postId", likeDto.getPostId());
        response.put("isLiked", isLikedNow);

        return ResponseEntity.ok(response);
    }
}
