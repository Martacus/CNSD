package com.mart.events.controller;

import com.mart.events.commander.PersonCommander;
import com.mart.events.model.PersonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("commandPersonsEndpoint")
@RequestMapping("/persons")
@Slf4j
public class PersonsCommandController {

    private final PersonCommander commander;

    public PersonsCommandController(PersonCommander commander) {
        this.commander = commander;
    }

    @PostMapping
    public UUID createPerson(@RequestBody PersonData data) {
        UUID id = commander.createPerson(data);
        return id;
    }

    @PutMapping("/{id}/date_of_death")
    public UUID createDeathDay(@RequestBody PersonData data) {
        UUID id = commander.createPerson(data);
        return id;
    }

}
