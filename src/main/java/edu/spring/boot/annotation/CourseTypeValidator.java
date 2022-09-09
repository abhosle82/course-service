package edu.spring.boot.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class CourseTypeValidator implements ConstraintValidator<CourseTypeValidation,String>{

    @Override
    public boolean isValid(String courseType, ConstraintValidatorContext context) {
        List<String> list = Arrays.asList("LIVE","RECORDING");
        return list.contains(courseType);
    }
}
