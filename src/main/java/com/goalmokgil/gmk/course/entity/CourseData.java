package com.goalmokgil.gmk.course.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CourseData {
    private String courseTitle;
    private List<Place> courseContent = new ArrayList<>();

    public void addPlace(Place place) {
        courseContent.add(place);
    }
}