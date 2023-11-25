package com.goalmokgil.gmk.course.service;

import com.goalmokgil.gmk.GmkApplication;
import com.goalmokgil.gmk.account.entity.Member;
import com.goalmokgil.gmk.account.repository.MemberRepository;
import com.goalmokgil.gmk.account.service.TokenService;
import com.goalmokgil.gmk.course.dto.CourseDto;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.repository.CourseRepository;
import com.goalmokgil.gmk.course.service.CourseService;
import com.goalmokgil.gmk.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
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
    @BeforeEach
    void setUp() {
        Mockito.reset(tokenService, memberRepository, courseRepository);
        openMocks(this);
    }
    @Test
    public void testGetCourseByCourseId() {
        String authorizationHeader = "Bearer your_token_here";
        Long courseId = 1L;
        Long userId = 123L; // Replace with your expected user ID

        Mockito.when(tokenService.getCurrentUserId(Mockito.anyString())).thenReturn(userId);

        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        // Act and Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            courseService.getCourseByCourseId(authorizationHeader, courseId);
        });
    }

    @Test
    public void testGetCourseByCourseIdNotFound() {
        // Arrange
        String authorizationHeader = "Bearer your_token_here";
        Long courseId = 1L;
        Long userId = 123L; // Replace with your expected user ID

        Mockito.when(tokenService.getCurrentUserId(Mockito.anyString())).thenReturn(userId);

        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> courseService.getCourseByCourseId(authorizationHeader, courseId));
    }

    @Test
    void testCreateNewCourse() {
        // Arrange
        String authorizationHeader = "Bearer your_token_here";
        CourseDto courseDto = new CourseDto();
        courseDto.setUserId(123L); // Replace with your expected user ID

        Long userIdFromToken = 123L; // Replace with your expected user ID
        Member existingMember = new Member(); // Replace with a mock or a real Member instance
        Course newCourse = new Course(); // Replace with a mock or a real Course instance

        Mockito.when(tokenService.getCurrentUserId(Mockito.any())).thenReturn(userIdFromToken);
        Mockito.when(memberRepository.findById(userIdFromToken)).thenReturn(java.util.Optional.of(existingMember));
        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenReturn(newCourse);
        // Act
        Course result = courseService.createNewCourse(authorizationHeader, courseDto);

        // Assert
        assertEquals(newCourse, result);
        assertEquals(existingMember.getCourse().size(), 1); // Assuming that adding a new course updates the member's courses
        Mockito.verify(memberRepository, Mockito.times(1)).save(existingMember);
        Mockito.verify(courseRepository, Mockito.times(1)).save(Mockito.any(Course.class));
    }

    @Test
    void testCreateNewCourseWithInvalidUser() {
        // Arrange
        String authorizationHeader = "Bearer your_token_here";
        CourseDto courseDto = new CourseDto();
        courseDto.setUserId(456L); // Replace with an expected user ID that doesn't match the token

        Long userIdFromToken = 123L; // Replace with your expected user ID
        Mockito.when(tokenService.getCurrentUserId(Mockito.anyString())).thenReturn(userIdFromToken);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> courseService.createNewCourse(authorizationHeader, courseDto));
    }
}
