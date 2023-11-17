package com.goalmokgil.gmk;

import com.goalmokgil.gmk.account.dto.req.ReqSignupDto;
import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.account.service.SignupService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class GmkApplicationTests {

	@Test
	void contextLoads() {
	}


	@Autowired
	SignupService signupService;
	@Autowired
	MemberRepository memberRepository;


//
//	@Test
//	void 회원가입() {
//
//		// given
//		Member member = new Member();
//		member.setId(1L);
//		member.setMemberId("아이디");
//		member.setPassword("비번");
//		member.setName("이름");
//		member.setNickname("닉넴");
//		member.setBirth("생일");
//		member.setEmail("이메일");
//
//		// when
//		String memberId = member.getMemberId();
//		// then
//		Optional findMember = memberRepository.findByMemberId(member.getMemberId());
//		Assertions.assertThat(member.getName()).isEqualTo(findMember.getClass());
//
//	}

}
