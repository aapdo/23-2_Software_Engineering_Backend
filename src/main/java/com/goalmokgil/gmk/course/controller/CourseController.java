package com.goalmokgil.gmk.course.controller;

import com.goalmokgil.gmk.course.dto.CourseDto;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;


    // member 관련 내용도 받을 거임.
    @PostMapping("create")
    public CourseDto createNewCourse(@RequestHeader("Authorization") String authorizationHeader, @RequestBody CourseDto courseDto) {
        return new CourseDto(courseService.createNewCourse(authorizationHeader, courseDto));

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
    public List<CourseDto> viewMyCourse(@RequestHeader("Authorization") String authorizationHeader) {
        ArrayList<CourseDto> returnList = new ArrayList<>();
        List<Course> result = courseService.getAllCourseByMemberId(authorizationHeader);
        for (Course course : result) {
            returnList.add(new CourseDto(course));
        }
        return returnList;
    }
}
