package com.example.demo.hateoas.adapter.presentation.dto;

import com.example.demo.hateoas.adapter.validation.group.FiveMultiple;
import com.example.demo.hateoas.adapter.validation.group.ThreeMultiple;
import com.example.demo.hateoas.adapter.validation.group.TwoMultiple;
import com.example.demo.hateoas.adapter.validation.group.marker.EvenValid;
import com.example.demo.hateoas.adapter.validation.group.marker.OddValid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Positive;
import javax.validation.groups.Default;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroupValidationDto {

    @TwoMultiple(groups = {Default.class, EvenValid.class})
    @Positive
    private int num1;

    @ThreeMultiple(groups = OddValid.class)
    private int num2;

    @FiveMultiple(groups = OddValid.class)
    private int num3;
}
