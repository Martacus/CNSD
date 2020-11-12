package com.mart.events.query;

import com.mart.events.event.PersonCreated;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PersonProjector {
    private PersonRegistry registry;

    public PersonProjector(PersonRegistry registry) {
        this.registry = registry;
    }

    @EventHandler
    public void handleEventCreated(PersonCreated personCreated){
        PersonData personData = new PersonData(personCreated.getId(), personCreated.getDateOfBirth());
        registry.save(personData);
        log.info("Person Data Created");
    }
}
