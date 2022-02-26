package com.example.demo.hateoas.application;

import com.example.demo.hateoas.adapter.presentation.dto.GroupValidationDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
@Log4j2
class GroupValidationServiceTest {
    @Autowired
    private GroupValidationService service;

    @Test
    void serveObject_1_0_2_0_3_0() {
        GroupValidationDto dto = new GroupValidationDto(2,3,5);

        service.serveObject(dto);
    }

    @Test
    void serveObject_1_1_2_0_3_0() {
        GroupValidationDto dto = new GroupValidationDto(1,3,5);

        Throwable t = catchThrowable(() -> service.serveObject(dto));
        assertThat(t).isInstanceOf(ConstraintViolationException.class);

        ConstraintViolationException ex = (ConstraintViolationException) t;
        for(var c: ex.getConstraintViolations()) {
            printConstraint(c);
        }
    }

    @Test
    void serveObject_1_2_2_0_3_0() {
        GroupValidationDto dto = new GroupValidationDto(-2,3,5);

        Throwable t = catchThrowable(() -> service.serveObject(dto));
        assertThat(t).isInstanceOf(ConstraintViolationException.class);

        ConstraintViolationException ex = (ConstraintViolationException) t;
        for(var c: ex.getConstraintViolations()) {
            printConstraint(c);
        }
    }

    @Test
    void serveObject_1_3_2_0_3_0() {
        GroupValidationDto dto = new GroupValidationDto(-1,3,5);

        Throwable t = catchThrowable(() -> service.serveObject(dto));
        assertThat(t).isInstanceOf(ConstraintViolationException.class);

        ConstraintViolationException ex = (ConstraintViolationException) t;
        for(var c: ex.getConstraintViolations()) {
            printConstraint(c);
        }
    }

    @Test
    void serveObject_1_1_2_1_3_1() {
        GroupValidationDto dto = new GroupValidationDto(1,4,6);

        Throwable t = catchThrowable(() -> service.serveObject(dto));
        assertThat(t).isInstanceOf(ConstraintViolationException.class);

        ConstraintViolationException ex = (ConstraintViolationException) t;
        for(var c: ex.getConstraintViolations()) {
            printConstraint(c);
        }
    }

    @Test
    void serveParameterWithDefaultValid_1_0_2_0_3_0() {
        service.serveParameterWithDefaultValid(30);
    }

    @Test
    void serveParameterWithDefaultValid_1_0_2_0_3_1() {
        Throwable t = catchThrowable(() -> service.serveParameterWithDefaultValid(6));
        assertThat(t).isInstanceOf(ConstraintViolationException.class);

        ConstraintViolationException ex = (ConstraintViolationException) t;
        for(var c: ex.getConstraintViolations()) {
            printConstraint(c);
        }
    }

    @Test
    void serveParameterWithDefaultValid_1_1_2_1_3_1() {
        Throwable t = catchThrowable(() -> service.serveParameterWithDefaultValid(1));
        assertThat(t).isInstanceOf(ConstraintViolationException.class);

        ConstraintViolationException ex = (ConstraintViolationException) t;
        for(var c: ex.getConstraintViolations()) {
            printConstraint(c);
        }
    }

    @Test
    void serveParameterWithoutDefaultValid_1_0_2_0_3_0() {
        service.serveParameterWithoutDefaultValid(30);
    }

    @Test
    void serveParameterWithoutDefaultValid_1_0_2_0_3_1() {
        service.serveParameterWithoutDefaultValid(6);
    }

    @Test
    void serveParameterWithoutDefaultValid_1_1_2_1_3_1() {
        Throwable t = catchThrowable(() -> service.serveParameterWithoutDefaultValid(1));
        assertThat(t).isInstanceOf(ConstraintViolationException.class);

        ConstraintViolationException ex = (ConstraintViolationException) t;
        for(var c: ex.getConstraintViolations()) {
            printConstraint(c);
        }
    }
    
    private void printConstraint(ConstraintViolation<?> c) {
        log.info("Property: {}, Constraint: {}, Message: {}", getPropertyName(c), getAnnotationName(c), c.getMessage());
    }

    private String getPropertyName(ConstraintViolation<?> violation) {
        String propertyPath = violation.getPropertyPath().toString();
        return propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
    }

    private String getAnnotationName(ConstraintViolation<?> violation) {
        return violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
    }
}