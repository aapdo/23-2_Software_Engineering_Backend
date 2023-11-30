package com.goalmokgil.gmk.post.repository;

import com.goalmokgil.gmk.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(String PostId);

    // 유저 아이디로 포스트 찾기 추가? -->> MemberRepository에서 해야하나
}
