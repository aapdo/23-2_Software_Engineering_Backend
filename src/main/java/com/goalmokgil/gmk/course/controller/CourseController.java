package com.goalmokgil.gmk.course.controller;

import com.goalmokgil.gmk.course.CourseValidator;
import com.goalmokgil.gmk.course.dto.CourseDto;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/course")
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
    @PostMapping("create")
    public boolean createNewCourse(@RequestBody CourseDto courseDto) {
        return true;
    }

    @GetMapping("view/{courseId}")
    public CourseDto viewCourse(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long courseId) {
        return new CourseDto(courseService.getCourseByCourseId(authorizationHeader, courseId));
    }

    /**
     * Member mypage에서 자신의 모든 코스를 조회하고 싶을 때 사용
     * @return ArrayList<Course>
     */
    @GetMapping("/myCourses")
    public String viewMyCourse(@RequestHeader("Authorization") String authorizationHeader) {

        return "1";
    }
}
