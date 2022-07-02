package com.solbegsoft.beersapi.annotations;


import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * Validator of Double type parameters in request parameters
 */
@Component
public class RequestBeersConstraintValidator implements ConstraintValidator<CustomRequestParamValidation, Double> {

    @Override
    public void initialize(CustomRequestParamValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return Objects.isNull(value) || (value >= 0 && value < 100);
    }
}