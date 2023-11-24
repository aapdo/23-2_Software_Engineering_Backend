package com.goalmokgil.gmk.course.mongo;

import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.entity.CourseTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
@EnableMongoRepositories(basePackageClasses=TestMongo.class)
@Component
public interface TestMongo extends MongoRepository<CourseTest, Long> {
}
