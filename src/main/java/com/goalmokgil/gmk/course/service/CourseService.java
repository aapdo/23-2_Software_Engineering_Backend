package com.goalmokgil.gmk.course.service;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.account.service.TokenService;
import com.goalmokgil.gmk.course.dto.CourseDto;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.repository.CourseRepository;
import com.goalmokgil.gmk.exception.ForbiddenException;
import com.goalmokgil.gmk.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@AllArgsConstructor
public class CourseService   {

    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    // member id, course id로 해당 코스를 조회하고 리턴함.
    // 없을 경우 빈 코스 템플릿을 생성하고 db에 저장 후 리턴.
    public Course getCourseByCourseId(Long userId, Long courseId){
        Optional<Course> courseByCourseId = courseRepository.findById(courseId);
        Course course = courseByCourseId.orElseThrow(EntityNotFoundException::new);
        log.info("view course, courseId: {}", course.getCourseId());
        // 삭제된 코스일 경우 null이 아님.
        if (course.getDeletedDate() != null) {
            throw new ForbiddenException("해당 코스는 삭제되었습니다.", HttpStatus.FORBIDDEN);
        }
        if (course.getMember().getUserId().equals(userId)) {
            return course;
        } else {
            throw new ForbiddenException("해당 코스에 대한 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
    }

    public List<CourseDto> getAllCourseByMemberId(Long userId){
        Member member = memberRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("잘못된 계정 정보입니다."));
        log.info("view myCourse, userId: {}", member.getUserId());
        ArrayList<CourseDto> result = new ArrayList<>();
        for (Course course : member.getCourses()) {
            // null 이면 삭제되지 않은 상태.
            if (course.getDeletedDate() == null) {
                log.info("course by member, courseId: {}", course.getCourseId());
                result.add(new CourseDto(course));
            }
        }
        return result;
    }

    @Transactional
    public Course createNewCourse(Long userId, CourseDto courseDto) {
        // 해당 member가 존재하지 않는 경우
        Member member = memberRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("잘못된 계정 정보입니다."));
        log.info("create course, userId: {} = ", member.getUserId());

        // You can use the userId or other information from the token to set properties of the new course
        Course newCourse = new Course(courseDto, member);
        member.getCourses().add(newCourse);
        courseRepository.save(newCourse);
        return newCourse;
    }


    @Transactional
    public Course updateCourse(Long userId, CourseDto updatedCourseDto) {

        Long courseId = updatedCourseDto.getCourseId();

        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 Course입니다."));
        log.info("update course, courseId: {}", existingCourse.getCourseId());

        Member member = memberRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("잘못된 계정 정보입니다."));
        log.info("update course, userId: {}", member.getUserId());

        if (!existingCourse.getMember().equals(member)) {
            throw new ForbiddenException("해당 코스에 대한 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        // null이 아닌 경우 삭제된 상태임.
        else if(existingCourse.getDeletedDate() != null){
            throw new ForbiddenException("해당 코스는 삭제되었습니다.", HttpStatus.FORBIDDEN);
        }
        existingCourse.updateCourse(updatedCourseDto);

        courseRepository.save(existingCourse);

        return existingCourse;
    }

    public Course deleteCourse(Long userId, Long courseId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("잘못된 계정 정보입니다."));
        log.info("delete courseService, userId: {}", member.getUserId());
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 Course입니다."));
        log.info("delete courseService, courseId: {}", course.getCourseId());
        if (!course.getMember().equals(member)) {
            throw new ForbiddenException("해당 코스에 대한 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        course.setDeletedDate(new Date());

        courseRepository.save(course);
        return course;
    }
}
