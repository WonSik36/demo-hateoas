package com.example.demo.hateoas.adapter.validation.group.marker;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({OddValid.class, Default.class, EvenValid.class})
public interface BaseSequence {
}