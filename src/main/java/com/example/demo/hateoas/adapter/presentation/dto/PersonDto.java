package com.example.demo.hateoas.adapter.presentation.dto;

import com.example.demo.hateoas.adapter.validation.NoBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {
    @NotBlank
    @NoBlank
    private String id;

    @NotBlank
    @NoBlank
    private String firstName;

    @NotBlank
    @NoBlank
    private String lastName;

    @Positive
    private int age;


}
