package com.example.demo.hateoas.adapter.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoBlankValidator implements ConstraintValidator<NoBlank, String> {
    public static final String BLANK = " ";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !value.contains(BLANK);
    }
}
