package com.goalmokgil.gmk.account.repository;

import com.goalmokgil.gmk.account.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String LoginId);

    // ID, 닉네임 중복검사
    boolean existsByLoginId(String memberId);
    boolean existsByNickname(String nickname);
}