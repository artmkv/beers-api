package com.solbegsoft.beersapi.annotations;


import com.solbegsoft.beersapi.configurations.ErrorMessageConstant;
import com.solbegsoft.beersapi.utils.MessageUtils;
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

    private final MessageUtils messageUtils;

    @Override
    public void initialize(CustomRequestParamValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        context.buildConstraintViolationWithTemplate(
                messageUtils.getMessage(ErrorMessageConstant.INVALID_RANGE_PARAMETER, value));
        return Objects.isNull(value) || (value >= 0 && value < 100);
    }
}