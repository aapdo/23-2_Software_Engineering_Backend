package com.goalmokgil.gmk.course.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.entity.CourseData;
import com.goalmokgil.gmk.course.entity.Place;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.assertj.core.api.Assertions;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
class CourseRepositoryTest {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void afterEach() {
        memberRepository.deleteAll();
        courseRepository.deleteAll();
    }

    @Test
    void saveCourse() throws JsonProcessingException {
        Member testMember = new Member("1", "1", "jy", "jy", "0830", "jade");
        testMember = memberRepository.save(testMember);

        Place place = new Place();
        place.setPlaceName("test name");
        place.setPlaceId(1L);
        LocalDateTime now = LocalDateTime.now();

        // DateTimeFormatter를 사용하여 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 포맷팅된 현재 시간 출력
        String formattedDate = now.format(formatter);
        place.setDate(new Date());
        CourseData courseData = new CourseData();
        courseData.addPlace(place);
        courseData.setCourseTitle("test title");

        Course course = new Course(1L, testMember, courseData, new Date(), new Date());

        //System.out.println("course = " + course);
        ObjectMapper objectMapper = new ObjectMapper();
        String test = objectMapper.writeValueAsString(place);
        System.out.println("test = " + test);
        test = objectMapper.writeValueAsString(course);
        System.out.println("test = " + test);
        //String testJson = objectMapper.writeValueAsString(course);
        courseRepository.save(course);

        Optional<Course> findCourse = courseRepository.findById(course.getCourseId());
        Course savedCourse = findCourse.orElse(new Course());

        Assertions.assertThat(savedCourse.getCourseId()).isEqualTo(course.getCourseId());
    }

    @Test
    void findAllByMemberIdTest(){
        /*
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

         */
    }
}