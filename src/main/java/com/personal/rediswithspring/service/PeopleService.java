package com.personal.rediswithspring.service;

import com.personal.rediswithspring.entity.Person;
import com.personal.rediswithspring.entity.Person$;
import com.redis.om.spring.search.stream.EntityStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import static redis.clients.jedis.search.aggr.SortedField.*;

@Service
@RequiredArgsConstructor
public class PeopleService {

    final EntityStream entityStream;

    public Iterable<Person> findAllPeople() {
        return entityStream
                .of(Person.class)
                .collect(Collectors.toList());
    }

    public Iterable<Person> findByAgeBetween(int minAge, int maxAge) {
        return entityStream
                .of(Person.class)
                .filter(Person$.AGE.between(minAge, maxAge))
                .sorted(Person$.AGE, SortOrder.ASC)
                .collect(Collectors.toList());
    }

    public Iterable<Person> findByFirstNameAndLastName(String firstName, String lastName) {
        return entityStream //
                .of(Person.class) //
                .filter(Person$.FIRST_NAME.eq(firstName)) //
                .filter(Person$.LAST_NAME.eq(lastName)) //
                .collect(Collectors.toList());
    }
}
