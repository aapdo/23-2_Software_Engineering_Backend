package com.goalmokgil.gmk.course;

import com.goalmokgil.gmk.course.entity.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Slf4j
public class CourseValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Course.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof Course) {
            Course course = (Course) target;
        } else {
            errors.rejectValue("target", "is not Course");
        }




    }
}
