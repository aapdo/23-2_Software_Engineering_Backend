package com.goalmokgil.gmk.post.service;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.post.entity.Likes;
import com.goalmokgil.gmk.post.entity.Post;
import com.goalmokgil.gmk.post.repository.LikesRepository;
import com.goalmokgil.gmk.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class LikeService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final LikesRepository likesRepository;

    @Transactional
    public boolean addLike(Long userId, Long postId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Optional<Likes> existingLike = likesRepository.findByMemberAndPost(member, post);

        log.info("post.likes: " + post.getLikes());

        if (existingLike.isPresent()) {
            // 이미 '좋아요'가 있으면 제거
            member.getLikes().remove(existingLike.get());
            post.getLikes().remove(existingLike.get());

            likesRepository.delete(existingLike.get());
            log.info("post.likes: " + post.getLikes());

            return false; // '좋아요' 제거됨
        }
        else { // '좋아요'가 없으면 추가
            Likes likes = new Likes();
            likes.setMember(member);
            likes.setPost(post);

            member.getLikes().add(likes);
            post.getLikes().add(likes);

            likesRepository.save(likes);
            log.info("post.likes" + post.getLikes());
            return true; // '좋아요' 추가됨
        }
    }

    public long countLikesByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return likesRepository.countByPost(post);
    }
}
