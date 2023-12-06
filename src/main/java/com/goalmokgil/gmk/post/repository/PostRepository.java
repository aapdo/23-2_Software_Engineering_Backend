package com.goalmokgil.gmk.post.repository;

import com.goalmokgil.gmk.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTags_Name(String tagName); // 태그 이름으로 포스트 찾기


    // 좋아요 수에 따라 포스트를 정렬하여 반환하는 쿼리
    @Query("SELECT p FROM Post p LEFT JOIN p.likes l GROUP BY p ORDER BY COUNT(l) DESC")
    List<Post> findAllOrderByLikesDesc();
}
