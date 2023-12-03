package com.goalmokgil.gmk.post.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tag {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "tags")
    @JsonBackReference
    private Set<Post> posts = new HashSet<>();

    public Tag(String name) {
        this.name = name;
    }
}
