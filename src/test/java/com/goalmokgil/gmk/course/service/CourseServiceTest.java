package com.goalmokgil.gmk.course.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goalmokgil.gmk.GmkApplication;
import com.goalmokgil.gmk.account.dto.TokenDto;
import com.goalmokgil.gmk.account.dto.req.ReqSignupDto;
import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.account.service.LoginService;
import com.goalmokgil.gmk.account.service.SignupService;
import com.goalmokgil.gmk.account.service.TokenService;
import com.goalmokgil.gmk.course.dto.CourseDto;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.entity.CourseData;
import com.goalmokgil.gmk.course.entity.Place;
import com.goalmokgil.gmk.course.repository.CourseRepository;
import com.goalmokgil.gmk.exception.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest(classes = GmkApplication.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private TokenService tokenService;

    @InjectMocks
    private CourseService courseService;

    @Autowired
    private SignupService signupService;
    @Autowired
    private LoginService loginService;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
        courseRepository.deleteAll();
        Mockito.reset(tokenService, memberRepository, courseRepository);
        MockitoAnnotations.openMocks(this);    }

    @Test
    public void testGetCourseByCourseIdNotFound() {
        // Arrange
        String authorizationHeader = "Bearer your_token_here";
        Long courseId = 1L;
        Long userId = 123L; // Replace with your expected user ID

        Mockito.when(tokenService.getCurrentUserId(Mockito.anyString())).thenReturn(userId);

        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> courseService.getCourseByCourseId(123L, courseId));
    }



    @Test
    void saveCourse() throws JsonProcessingException, JsonProcessingException {
        String loginId = generateRandomString(4);
        signupService.memberSignup(new ReqSignupDto(loginId, "1234", "jy", "testjy", "20000830", "jade@naver.com"));
        TokenDto tokenDto = loginService.login(loginId, "1234");

        String authorizationHeader = tokenDto.getAccessToken();
        System.out.println("authorizationHeader = " + authorizationHeader);

        Place place = new Place();
        place.setPlaceName("test name");
        place.setPlaceId(1L);
        place.setDate(new Date());
        CourseData courseData = new CourseData();
        courseData.addPlace(place);
        courseData.setCourseTitle("test title");

        Long userId = tokenService.getCurrentUserIdByAuthorizationHeader(authorizationHeader);

        CourseDto courseDto = new CourseDto(null,  userId, courseData, new Date(), new Date());

        ObjectMapper objectMapper = new ObjectMapper();
        String courseDtoStr = objectMapper.writeValueAsString(courseDto);
        System.out.println("courseDtoStr = " + courseDtoStr);
        System.out.println("courseDto = " + courseDto);

        Course savedCourse = courseService.createNewCourse(userId, courseDto);

        System.out.println("savedCourse = " + savedCourse);

        //Assertions.assertThat(savedCourse.getCourseId()).isEqualTo(course.getCourseId());
    }


}
