package com.solbegsoft.beersapi.annotations;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;


@Component
public class RequestBeersConstraintValidator implements ConstraintValidator<CustomRequestParamValidation, Double> {

    @Override
    public void initialize(CustomRequestParamValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }
        if (value < 0) {
            return false;
        }
        return value < 100;
    }
}