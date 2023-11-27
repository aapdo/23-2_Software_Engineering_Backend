package com.goalmokgil.gmk.course.service;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.account.service.TokenService;
import com.goalmokgil.gmk.course.dto.CourseDto;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.repository.CourseRepository;
import com.goalmokgil.gmk.exception.ForbiddenCourseException;
import com.goalmokgil.gmk.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@AllArgsConstructor
public class CourseService   {

    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    public ArrayList<Course> getMemberCourses(Long userId){
        //return courseRepository.findAllByUserId(userId);
        return new ArrayList<>();
    }

    // member id, course id로 해당 코스를 조회하고 리턴함.
    // 없을 경우 빈 코스 템플릿을 생성하고 db에 저장 후 리턴.
    public Course getCourseByCourseId(String authorizationHeader, Long courseId){
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getCurrentUserId(token);
        Optional<Course> courseByCourseId = courseRepository.findById(courseId);
        Course course = courseByCourseId.orElseThrow(EntityNotFoundException::new);
        if (course.getMember().getUserId().equals(userId)) {
            return course;
        }else {
            throw new ForbiddenCourseException("해당 코스를 조회할 수 있는 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
    }

    public List<Course> getAllCourseByMemberId(String authorizationHeader){
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getCurrentUserId(token);
        Member member = memberRepository.findById(userId).orElseThrow(() -> {
            return new EntityNotFoundException("잘못된 계정 정보입니다.");
        });
        return member.getCourse();
    }

    public Course createNewCourse(String authorizationHeader, CourseDto courseDto) {
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getCurrentUserId(token);
        // course Dto에 저장된 userId와 로그인 토큰에 저장된 아이디가 다른 경우
        if (!userId.equals(courseDto.getUserId())) {
            throw new EntityNotFoundException("잘못된 계정 정보입니다.");
        }
        // 해당 member가 존재하지 않는 경우
        Member member = memberRepository.findById(userId).orElseThrow(() -> {
            return new EntityNotFoundException("잘못된 계정 정보입니다.");
        });
        // You can use the userId or other information from the token to set properties of the new course
        Course newCourse = new Course(courseDto, member);
        // member의 course에 add 해줌.
        member.getCourse().add(newCourse);
        memberRepository.save(member);
        courseRepository.save(newCourse);
        return newCourse;
    }


}
