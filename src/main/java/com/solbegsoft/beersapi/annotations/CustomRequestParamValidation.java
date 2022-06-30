package com.solbegsoft.beersapi.annotations;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Validation of values of type Double request parameters
 */
@Documented
@Constraint(validatedBy = RequestBeersConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomRequestParamValidation {

    String message() default "The parameter value is out of range. Valid value  0 =< (number) < 100";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
