package com.goalmokgil.gmk.post.repository;

import com.goalmokgil.gmk.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    // 특정 회원이 작성한 모든 게시글 조회
    List<Post> findByAuthorUserId(Long userId);

    // 특정 코스에 연관된 모든 게시글 조회
    List<Post> findByRelatedCourseCourseId(Long courseId);

    // 제목으로 게시글 검색
    List<Post> findByTitleContaining(String title);

    // 특정 회원이 작성하고 특정 코스에 연관된 게시글 조회
    Optional<Post> findByAuthorUserIdAndRelatedCourseCourseId(Long userId, Long courseId);

}
