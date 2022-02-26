package com.example.demo.hateoas.adapter.presentation;

import com.example.demo.hateoas.adapter.presentation.dto.PersonModel;
import com.example.demo.hateoas.domain.person.Person;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/model")
@RestController
public class ModelController {

//    {"_embedded":{"personModels":[{"id":"id1","firstName":"firstname","lastName":"lastname","age":1,"_links":{"self":{"href":"http://localhost:8080/model/id1"}}},{"id":"id2","firstName":"firstname","lastName":"lastname","age":2,"_links":{"self":{"href":"http://localhost:8080/model/id2"}}},{"id":"id3","firstName":"firstname","lastName":"lastname","age":3,"_links":{"self":{"href":"http://localhost:8080/model/id3"}}}]},"_links":{"self":{"href":"http://localhost:8080/model/"}}}
    @GetMapping("/")
    public CollectionModel<PersonModel> getPersons(PersonModel.Assembler assembler) {
        List<Person> persons = personList();

        Link selfRel = linkTo(methodOn(ModelController.class).getPersons(assembler)).withSelfRel();

        return assembler.toCollectionModel(persons)
                .add(selfRel);
    }

//    {"id":"123","firstName":"firstname","lastName":"lastname","age":123,"_links":{"self":{"href":"http://localhost:8080/model/123"},"person-list":{"href":"http://localhost:8080/model/"}}}
    @GetMapping("/{id}")
    public PersonModel getPerson(PersonModel.Assembler assembler, @PathVariable String id) {
        Person person = new Person(id, "firstname", "lastname", 123);

        Link listRel = linkTo(methodOn(ModelController.class).getPersons(assembler)).withRel("person-list");

        return assembler.toModel(person)
                .add(listRel);
    }

    @GetMapping("/{id}/requestParam")
    public PersonModel getPersonWithRequestParam(PersonModel.Assembler assembler, @PathVariable String id, @RequestParam String name) {
        Person person = new Person(id, "firstname", "lastname", 123);

        return assembler.toModel(person);
    }

//    {"id":"123","firstName":"firstname","lastName":"lastname","age":123,"_embedded":{"somePersonModel":{"id":"someid","firstName":"someperson","lastName":"lastname","age":123,"_links":{"some-person":{"href":"http://localhost:8080/model/someid/requestParam?name=somename"}}}}}
    @GetMapping("/{id}/embed")
    public RepresentationModel<PersonModel> getPersonWithEmbed(@PathVariable String id) {
        Person person = new Person(id, "firstname", "lastname", 123);

        PersonModel somePersonModel = new PersonModel("someid", "someperson", "lastname", 123);
        Link somePersonLink = linkTo(methodOn(ModelController.class).getPersonWithRequestParam(new PersonModel.Assembler(), "someid", "somename")).withRel("some-person");
        somePersonModel.add(somePersonLink);

        return HalModelBuilder.halModelOf(PersonModel.of(person))
                .embed(somePersonModel)
                .build();
    }


    @GetMapping("/{id}/param")
    public PersonModel getPersonWithParam(PersonModel.Assembler assembler, @PathVariable String id, String name) {
        Person person = new Person(id, "firstname", "lastname", 123);

        Link selfRel = linkTo(methodOn(ModelController.class).getPersonWithParam(assembler, id, name)).withSelfRel();

        return assembler.toModel(person);
    }

    private List<Person> personList() {
        return List.of(
                new Person("id1","firstname", "lastname", 1),
                new Person("id2","firstname", "lastname", 2),
                new Person("id3","firstname", "lastname", 3)
        );
    }

}
