package com.mart.events.query;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController("queryPersonsEndpoint")
@RequestMapping(path = "/persons", method = GET)
@Slf4j
public class PersonsController {

    @Autowired
    QueryGateway queryGateway;

    @GetMapping("/{id}")
    public CompletableFuture<PersonData> findPerson(@PathVariable UUID id){
        FindPerson findPerson = new FindPerson(id);
        return queryGateway.query(findPerson, PersonData.class);
    }

}
