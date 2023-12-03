package com.goalmokgil.gmk.course.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CourseData {
    private String courseTitle;
    //private List<Place> courseContent = new ArrayList<>();
    private List< List<Place> > courseContent = new ArrayList<List<Place>>();

    public void addPlace(List<Place> place) {
        courseContent.add(place);
    }
}