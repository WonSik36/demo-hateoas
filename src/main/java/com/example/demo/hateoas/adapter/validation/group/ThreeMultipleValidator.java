package com.example.demo.hateoas.adapter.validation.group;

import lombok.extern.log4j.Log4j2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Log4j2
public class ThreeMultipleValidator implements ConstraintValidator<ThreeMultiple, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        log.info("Validate Number is multiple of three.");
        return value % 3 == 0;
    }
}
