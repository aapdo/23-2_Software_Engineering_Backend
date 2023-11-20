package com.goalmokgil.gmk.course.controller;

import com.goalmokgil.gmk.course.CourseValidator;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CourseController {

    private final CourseService courseService;
    private final CourseValidator courseValidator;

    /*
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(courseValidator);
    }

     */


    // member 관련 내용도 받을 거임.
    @PostMapping("create/course")
    public boolean createNewCourse(@RequestBody Course course) {

        return true;
    }

    @GetMapping("view/course/{courseId}")
    public String viewCourse(@PathVariable Long courseId) {
        // 유저 권한 체크 먼저 필요
        //return courseService.getMemberCourses(memberId)
        return "1";
    }

    /**
     * Member mypage에서 자신의 모든 코스를 조회하고 싶을 때 사용
     * @return ArrayList<Course>
     */
    @GetMapping("view/myCourse")
    public String viewMyCourse() {
        // 유저 권한 체크 먼저 필요
        // courseRepository.findAllByMemberId();
        courseService.getMemberCourses(1L);
        return "1";
    }
}
