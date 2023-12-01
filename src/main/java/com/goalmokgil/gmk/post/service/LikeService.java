package com.goalmokgil.gmk.post.service;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.post.entity.Likes;
import com.goalmokgil.gmk.post.entity.Post;
import com.goalmokgil.gmk.post.repository.LikeRepository;
import com.goalmokgil.gmk.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.sql.Like;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class LikeService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;

    public boolean addLike(Long userId, Long postId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!likeRepository.existsByMemberAndPost(member, post)) {
            Likes likes = new Likes();
            likes.setMember(member);
            likes.setPost(post);
            likeRepository.save(likes);
            return true; // '좋아요'가 추가됨
        }
        return false; // 이미 '좋아요'가 있음
    }
}
