package com.example.demo.hateoas.adapter.presentation.dto;

import com.example.demo.hateoas.adapter.presentation.ModelController;
import com.example.demo.hateoas.domain.person.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Relation(itemRelation = "somePersonModel",collectionRelation="personModels")
public class PersonModel extends RepresentationModel<PersonModel> {
    private String id;
    private String firstName;
    private String lastName;
    private int age;

    public static PersonModel of(Person person) {
        return new PersonModel(person.getId(), person.getFirstName(), person.getLastName(), person.getAge());
    }

    public static class Assembler extends RepresentationModelAssemblerSupport<Person, PersonModel> {
        public Assembler() {
            super(ModelController.class, PersonModel.class);
        }

        @Override
        public PersonModel toModel(Person person) {
            return PersonModel.of(person)
                    .add(linkTo(methodOn(ModelController.class).getPerson(this, person.getId())).withSelfRel());
        }
    }
}
