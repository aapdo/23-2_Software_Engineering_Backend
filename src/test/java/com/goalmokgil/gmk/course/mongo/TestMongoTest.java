package com.goalmokgil.gmk.course.mongo;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.course.entity.CourseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@DataJpaTest
@ExtendWith(SpringExtension.class)
class TestMongoTest {
    @Autowired
    TestMongo testMongo;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void createTest(){
        Member testMember = new Member("1", "1", "jy", "jy", "0830", "jade");
        testMember = memberRepository.save(testMember);
        testMongo.save(new CourseTest(1L, testMember, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()));
    }

}