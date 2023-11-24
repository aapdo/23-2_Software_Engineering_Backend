package com.goalmokgil.gmk.course.service;

import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;


@Service
@Slf4j
@AllArgsConstructor
public class CourseService   {

    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

    public ArrayList<Course> getMemberCourses(Long userId){
        return courseRepository.findAllByUserId(userId);
    }

    // member id, course id로 해당 코스를 조회하고 리턴함.
    // 없을 경우 빈 코스 템플릿을 생성하고 db에 저장 후 리턴.
    public Course getCourseByCourseId(Long memberId, Long courseId) {
        return new Course();
        //return courseRepository.findCourseByCourseIdAndMember(memberId, courseId).orElse(createNewCourseTemplate(memberId, courseId));
    }

    private Course createNewCourseTemplate(Long memberId, Long courseId) {
        return new Course();
        //return courseRepository.save(new Course(courseId, memberRepository.findById(memberId).orElseThrow(), LocalDateTime.now(), LocalDateTime.now()));
    }


}
