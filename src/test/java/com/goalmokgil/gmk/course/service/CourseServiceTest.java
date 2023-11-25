package com.goalmokgil.gmk.course.service;

import com.goalmokgil.gmk.GmkApplication;
import com.goalmokgil.gmk.account.service.TokenService;
import com.goalmokgil.gmk.course.entity.Course;
import com.goalmokgil.gmk.course.repository.CourseRepository;
import com.goalmokgil.gmk.course.service.CourseService;
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
    private TokenService tokenService;

    @InjectMocks
    private CourseService courseService;
    @BeforeEach
    void setUp() {
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
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
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
        assertThrows(NoSuchElementException.class, () -> courseService.getCourseByCourseId(authorizationHeader, courseId));
    }
}
