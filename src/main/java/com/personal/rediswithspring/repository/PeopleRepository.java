package com.personal.rediswithspring.repository;

import com.personal.rediswithspring.entity.Person;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import java.util.Set;

public interface PeopleRepository extends RedisDocumentRepository<Person, String> {
    Iterable<Person> findByAgeBetween(Integer minAge, Integer maxAge);
    Iterable<Person> findByFirstNameAndLastName(String firstName, String lastName);
    Iterable<Person> findByHomeLocNear(Point point, Distance distance);
    Iterable<Person> searchByPersonalStatement(String text);
    Iterable<Person> findByAddress_City(String city);
    Iterable<Person> findBySkills(Set<String> skills);
}
