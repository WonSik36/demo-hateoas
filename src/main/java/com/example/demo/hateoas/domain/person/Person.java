package com.example.demo.hateoas.domain.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Relation(itemRelation = "somePerson", collectionRelation="persons")
public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
}
