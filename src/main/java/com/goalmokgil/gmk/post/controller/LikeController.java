package com.goalmokgil.gmk.post.controller;

import com.goalmokgil.gmk.account.service.TokenService;
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
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> addLike(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long postId) {

        Long userId = tokenService.getCurrentUserIdByAuthorizationHeader(authorizationHeader);
        boolean isLikedNow = likeService.addLike(userId, postId);

        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("postId", postId);
        response.put("isLiked", isLikedNow);

        return ResponseEntity.ok(response);
    }
}
