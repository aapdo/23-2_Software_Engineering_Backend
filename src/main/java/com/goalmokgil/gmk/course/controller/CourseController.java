package com.goalmokgil.gmk.course.controller;

import com.goalmokgil.gmk.account.service.TokenService;
import com.goalmokgil.gmk.course.dto.CourseDto;
import com.goalmokgil.gmk.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final TokenService tokenService;


    // member 관련 내용도 받을 거임.
    @PostMapping("/create")
    public ResponseEntity<CourseDto> createNewCourse(@RequestHeader("Authorization") String authorizationHeader, @RequestBody CourseDto courseDto) {
        Long userId = tokenService.getCurrentUserIdByAuthorizationHeader(authorizationHeader);
        return ResponseEntity.ok(new CourseDto(courseService.createNewCourse(userId, courseDto)));
    }

    @PostMapping("/update")
    public ResponseEntity<CourseDto> updateCourse(@RequestHeader("Authorization") String authorizationHeader, @RequestBody CourseDto courseDto) {
        Long userId = tokenService.getCurrentUserIdByAuthorizationHeader(authorizationHeader);
        return ResponseEntity.ok(new CourseDto(courseService.updateCourse(userId, courseDto)));
    }

    @PostMapping("/delete")
    public ResponseEntity<CourseDto> deleteCourse(@RequestHeader("Authorization") String authorizationHeader, @RequestBody CourseDto courseDto) {
        Long userId = tokenService.getCurrentUserIdByAuthorizationHeader(authorizationHeader);
        return ResponseEntity.ok(new CourseDto(courseService.deleteCourse(userId, courseDto)));
    }


    @GetMapping("view/{courseId}")
    public ResponseEntity<CourseDto> viewCourse(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long courseId) {
        Long userId = tokenService.getCurrentUserIdByAuthorizationHeader(authorizationHeader);
        return ResponseEntity.ok(new CourseDto(courseService.getCourseByCourseId(userId, courseId)));
    }

    /**
     * Member mypage에서 자신의 모든 코스를 조회하고 싶을 때 사용
     * @return ArrayList<Course>
     */
    @GetMapping("/myCourses")
    public ResponseEntity<List<CourseDto>> viewMyCourse(@RequestHeader("Authorization") String authorizationHeader) {
        Long userId = tokenService.getCurrentUserIdByAuthorizationHeader(authorizationHeader);
        return ResponseEntity.ok(courseService.getAllCourseByMemberId(userId));
    }
}
