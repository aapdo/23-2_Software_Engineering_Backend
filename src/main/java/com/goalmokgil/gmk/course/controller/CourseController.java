package com.goalmokgil.gmk.course.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CourseController {

    @GetMapping("view/course/{courseId}")
    public String viewCourse(@PathVariable int courseId) {
        // 유저 권한 체크 먼저 필요
        //
        return "1";
    }

}
