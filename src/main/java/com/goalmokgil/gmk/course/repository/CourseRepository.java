package com.goalmokgil.gmk.course.repository;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
    ArrayList<Course> findAllByMemberId(Long id);

    Optional<Course> findCourseByCourseId(Long courseId);

}
