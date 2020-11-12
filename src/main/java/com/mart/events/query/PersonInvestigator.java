package com.mart.events.query;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonInvestigator {
    PersonRegistry personRegistry;

    public PersonInvestigator(PersonRegistry personRegistry) {
        this.personRegistry = personRegistry;
    }

    @QueryHandler
    public PersonData findPerson(FindPerson findPerson){
        Optional<PersonData> data =personRegistry.findById(findPerson.id);
        return data.orElse(null);
    }
}

