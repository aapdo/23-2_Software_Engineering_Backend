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


    // 모든 포스트를 좋아요 순으로 정렬
    @Query("SELECT p, COUNT(l) as likeCount FROM Post p LEFT JOIN p.likes l GROUP BY p.postId ORDER BY likeCount DESC")
    List<Post> findAllPostsOrderByLikes();


    // 위에거 안되면 이거?
//    @Query("SELECT p FROM Post p LEFT JOIN p.likes l GROUP BY p.postId ORDER BY COUNT(l) DESC")
//    List<Post> findAllOrderByLikes();
}
