package com.goalmokgil.gmk.post.controller;

import com.goalmokgil.gmk.post.dto.req.ReqLikeDto;
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
    public ResponseEntity<?> addLike(@RequestBody ReqLikeDto reqLikeDto) {
        boolean isLikedNow = likeService.addLike(reqLikeDto.getUserId(), reqLikeDto.getPostId());

        Map<String, Object> response = new HashMap<>();
        response.put("userId", reqLikeDto.getUserId());
        response.put("postId", reqLikeDto.getPostId());
        response.put("isLiked", isLikedNow);

        return ResponseEntity.ok(response);
    }
}
