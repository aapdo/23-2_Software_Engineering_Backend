package com.goalmokgil.gmk.post.repository;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.post.entity.Likes;
import com.goalmokgil.gmk.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {
    boolean existsByMemberAndPost(Member member, Post post);
}
