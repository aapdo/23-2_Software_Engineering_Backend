package com.goalmokgil.gmk.course.repository;

import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.course.mongo.TestMongo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@EnableAutoConfiguration
@SpringJUnitConfig(TestMongo.class)
@DataMongoTest
class CourseRepositoryMongoTest {
    @Test
    void createTest(){
        Member testMember = new Member("1", "1", "jy", "jy", "0830", "jade");
    }

}