package com.goalmokgil.gmk.course.repository;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.course.entity.Course;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
class CourseRepositoryTest {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void findAllByMemberIdTest(){
        Member testMember = new Member("1", "1", "jy", "jy", "0830", "jade");
        testMember = memberRepository.save(testMember);
        System.out.println("testMember = " + testMember);
        ArrayList<Course> courses = new ArrayList<>();
        int testCase = 10;
        for (int i = 0; i < testCase; i++) {
            courses.add(new Course((long)i, testMember, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()));
            System.out.println("courses = " + courses.get(i));
            courseRepository.save(courses.get(i));
        }

        ArrayList<Course> result = courseRepository.findAllByMemberId(testMember.getId());
        Assertions.assertThat(result.size() == testCase);
    }
    @Test
    void findAllByMemberIdTest2(){
        Member testMember = new Member("1", "1", "jy", "jy", "0830", "jade");
        testMember = memberRepository.save(testMember);
        ArrayList<Course> courses = new ArrayList<>();
        int testCase = 10;
        for (int i = 0; i < testCase; i++) {
            courses.add(new Course((long)i, testMember, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()));
            courseRepository.save(courses.get(i));
        }

        ArrayList<Course> result = courseRepository.findAllByMemberId(testMember.getId());
        Assertions.assertThat(result.get(1).getMember().equals(testMember));
    }
}