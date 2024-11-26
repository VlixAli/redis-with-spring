package com.personal.rediswithspring.controller;

import com.personal.rediswithspring.entity.Person;
import com.personal.rediswithspring.repository.PeopleRepository;
import com.personal.rediswithspring.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/people")
@RequiredArgsConstructor
public class PeopleControllerV1 {

    final PeopleRepository repo;
    private final PeopleService peopleService;

    @GetMapping("all")
    Iterable<Person> all() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    Optional<Person> findById(@PathVariable String id) {
        return repo.findById(id);
    }

    @GetMapping("age_between")
    Iterable<Person> byAgeBetween( //
                                   @RequestParam("min") int min, //
                                   @RequestParam("max") int max) {
        return repo.findByAgeBetween(min, max);
    }

    @GetMapping("name")
    Iterable<Person> byFirstNameAndLastName(@RequestParam("first") String firstName, //
                                            @RequestParam("last") String lastName) {
        return repo.findByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("homeloc")
    Iterable<Person> byHomeLoc(@RequestParam("lat") double lat, @RequestParam("lon") double lon,
                               @RequestParam("d") double distance) {
        return repo.findByHomeLocNear(new Point(lon, lat), new Distance(distance, Metrics.MILES));
    }

    @GetMapping("statement")
    Iterable<Person> byPersonalStatement(@RequestParam("q") String q) {
        return repo.searchByPersonalStatement(q);
    }

    @GetMapping("city")
    Iterable<Person> byCity(@RequestParam("city") String city) {
        return repo.findByAddress_City(city);
    }

    @GetMapping("skills")
    Iterable<Person> byAnySkills(@RequestParam("skills") Set<String> skills) {
        return repo.findBySkills(skills);
    }

    @GetMapping("all-people")
    Iterable<Person> allPeople() {
        return peopleService.findAllPeople();
    }

    @GetMapping("people-age_between")
    Iterable<Person> peopleByAgeBetween(@RequestParam("min") int min, @RequestParam("max") int max) {
        return peopleService.findByAgeBetween(min, max);
    }

    @GetMapping("people-name")
    Iterable<Person> peopleByFirstNameAndLastName(@RequestParam("first") String firstName,
                                                  @RequestParam("last") String lastName) {
        return peopleService.findByFirstNameAndLastName(firstName, lastName);
    }
}
