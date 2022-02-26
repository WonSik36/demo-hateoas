package com.example.demo.hateoas.adapter.validation.group;

import com.example.demo.hateoas.adapter.validation.group.marker.BaseSequence;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Validated(BaseSequence.class)      // BaseSequence.class vs value = {OddValid.class, Default.class, EvenValid.class}
@Documented                         // BaseSequence: 중간에 검증이 실패하면 *바로* 끝
public @interface MultipleGroup {   // value = {OddValid.class, Default.class, EvenValid.class}: 전체 검증 실행
}
