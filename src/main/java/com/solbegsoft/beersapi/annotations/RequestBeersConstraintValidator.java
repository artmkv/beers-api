package com.solbegsoft.beersapi.annotations;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * Validator of Double type parameters in request parameters
 */
@Component
@RequiredArgsConstructor
public class RequestBeersConstraintValidator implements ConstraintValidator<CustomRequestParamValidation, Double> {
    /**
     * Initialize validator
     * @param constraintAnnotation annotation
     */
    @Override
    public void initialize(CustomRequestParamValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     *  is valid parameters
     * @param value of parameter
     * @param context of Constraint validator
     * @return true/false
     */
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return Objects.isNull(value) || (value >= 0 && value < 100);
    }
}