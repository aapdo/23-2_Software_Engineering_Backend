package com.goalmokgil.gmk.post.entity;

import com.goalmokgil.gmk.course.entity.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class PostContent {
    private String courseTitle;
    private List<Place> places = new ArrayList<>();

    private List<String> pictures = new ArrayList<>();
    private String content;

}
