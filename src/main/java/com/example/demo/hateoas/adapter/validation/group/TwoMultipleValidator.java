package com.example.demo.hateoas.adapter.validation.group;

import lombok.extern.log4j.Log4j2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Log4j2
public class TwoMultipleValidator implements ConstraintValidator<TwoMultiple, Integer> {
    @Override
    public void initialize(TwoMultiple constraintAnnotation) {
        log.info("TwoMultipleValidator Initialized");
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        log.info("Validate Number is multiple of two.");
        return value % 2 == 0;
    }
}
