package com.goalmokgil.gmk.post.repository;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.post.entity.Likes;
import com.goalmokgil.gmk.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByMemberAndPost(Member member, Post post); // 좋아요 눌렀는지 확인 - 좋아요누르고, 취소하는 걸 위함
    long countByPost(Post post); // 특정 포스트의 좋아요 개수 리턴
}
