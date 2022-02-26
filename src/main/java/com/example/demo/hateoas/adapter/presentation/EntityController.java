package com.example.demo.hateoas.adapter.presentation;

import com.example.demo.hateoas.domain.person.Person;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/entity")
@RestController
public class EntityController {

    @GetMapping("/")
    public CollectionModel<Person> getPersons() {
        List<Person> persons = personList();

        Link selfRel = linkTo(methodOn(EntityController.class).getPersons()).withSelfRel();

        return CollectionModel.of(persons)
                .add(selfRel);
    }

    @GetMapping("/{id}")
    public EntityModel<Person> getPerson(@PathVariable String id) {
        Person person = new Person(id, "firstname", "lastname", 123);

        Link selfRel = linkTo(methodOn(EntityController.class).getPerson(id)).withSelfRel();

        return EntityModel.of(person)
                .add(selfRel);
    }

    @GetMapping("/{id}/requestParam")
    public EntityModel<Person> getPersonWithRequestParam(@PathVariable String id, @RequestParam String name) {
        Person person = new Person(id, "firstname", "lastname", 123);

        Link selfRel = linkTo(methodOn(EntityController.class).getPersonWithRequestParam(id, name)).withSelfRel();

        return EntityModel.of(person)
                .add(selfRel);
    }

    @GetMapping("/{id}/param")
    public EntityModel<Person> getPersonWithParam(@PathVariable String id, String name) {
        Person person = new Person(id, "firstname", "lastname", 123);

        Link selfRel = linkTo(methodOn(EntityController.class).getPersonWithParam(id, name)).withSelfRel();

        return EntityModel.of(person)
                .add(selfRel);
    }

    private List<Person> personList() {
        return List.of(
                new Person("id1","firstname", "lastname", 1),
                new Person("id2","firstname", "lastname", 2),
                new Person("id3","firstname", "lastname", 3)
        );
    }

}
