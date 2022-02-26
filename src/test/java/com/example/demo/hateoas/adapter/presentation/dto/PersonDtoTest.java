package com.example.demo.hateoas.adapter.presentation.dto;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
class PersonDtoTest {

    @Test
    @DisplayName("Not Blank Validator 확인")
    void validateTest() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        PersonDto personDto = PersonDto.builder()
                .age(10)
                .firstName("first name")    // 1
                .lastName("last name")      // 1
                .id("   ")                  // 2
                .build();

        Set<ConstraintViolation<PersonDto>> constraintViolations = validator.validate(personDto);
        assertThat(constraintViolations.size()).isEqualTo(4);

        for(var c: constraintViolations) {
            log.info("{}", c.getMessage());
        }
    }

    @Test
    @DisplayName("Locale 비교: US")
    void locale_compare_US() {
        Locale.setDefault(Locale.US);

        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        PersonDto personDto = PersonDto.builder()
                .age(10)
                .firstName("firstname")
                .lastName("lastname")
                .id("   ")              // 2
                .build();

        Set<ConstraintViolation<PersonDto>> constraintViolations = validator.validate(personDto);
        assertThat(constraintViolations.size()).isEqualTo(2);

        ConstraintViolation<PersonDto> violation = constraintViolations.iterator().next();
        for(var c: constraintViolations) {
            log.info("{}: {}", getPropertyName(c), c.getMessage());
        }
    }

    @Test
    @DisplayName("Locale 비교: KOREA")
    void locale_compare_KO() {
        Locale.setDefault(Locale.KOREA);

        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        PersonDto personDto = PersonDto.builder()
                .age(10)
                .firstName("firstname")
                .lastName("lastname")
                .id("   ")              // 2
                .build();

        Set<ConstraintViolation<PersonDto>> constraintViolations = validator.validate(personDto);
        assertThat(constraintViolations.size()).isEqualTo(2);

        for(var c: constraintViolations) {
            log.info("{}: {}", getPropertyName(c), c.getMessage());
        }

        BeanWrapperImpl beanWrapper;
    }

    private String getPropertyName(ConstraintViolation<?> violation) {
        String propertyPath = violation.getPropertyPath().toString();
        return propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
    }
}