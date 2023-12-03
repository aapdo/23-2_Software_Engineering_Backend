package com.goalmokgil.gmk.course.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.entity.CourseData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private Long courseId;
    private Long userId;
    private CourseData courseData;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedDate;

    public CourseDto(Course course) {
        this.courseId = course.getCourseId();
        this.userId = course.getMember().getUserId();
        this.courseData = course.getCourseData();
        this.createdDate = course.getCreatedDate();
        this.modifiedDate = course.getModifiedDate();
    }
}
