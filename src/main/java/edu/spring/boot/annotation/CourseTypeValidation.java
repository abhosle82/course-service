package edu.spring.boot.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CourseTypeValidator.class)
public @interface CourseTypeValidation {
    public String message() default "Course Type can be only LIVE or RECORDING";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
