package com.example.demo.hateoas.application;

import com.example.demo.hateoas.adapter.presentation.dto.GroupValidationDto;
import com.example.demo.hateoas.adapter.validation.group.FiveMultiple;
import com.example.demo.hateoas.adapter.validation.group.MultipleGroup;
import com.example.demo.hateoas.adapter.validation.group.ThreeMultiple;
import com.example.demo.hateoas.adapter.validation.group.TwoMultiple;
import com.example.demo.hateoas.adapter.validation.group.marker.EvenValid;
import com.example.demo.hateoas.adapter.validation.group.marker.OddValid;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.groups.Default;

@Service
@Validated
@Log4j2
public class GroupValidationService {

    @MultipleGroup
    public void serveObject(@Valid GroupValidationDto dto) {
        log.info("serveObject: {}", dto);
    }

    @MultipleGroup
    public void serveParameterWithDefaultValid(
            @TwoMultiple(groups = EvenValid.class)
            @ThreeMultiple(groups = OddValid.class)
            @FiveMultiple(groups = Default.class) int num
    ) {
        log.info("serveParameterWithDefaultValid: {}", num);
    }

    @Validated(value = {EvenValid.class, OddValid.class})
    public void serveParameterWithoutDefaultValid(
            @TwoMultiple(groups = EvenValid.class)
            @ThreeMultiple(groups = OddValid.class)
            @FiveMultiple(groups = Default.class) int num
    ) {
        log.info("serveParameterWithoutDefaultValid: {}", num);
    }

    public void test() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(BasicPolymorphicTypeValidator.builder().build());
    }
}
