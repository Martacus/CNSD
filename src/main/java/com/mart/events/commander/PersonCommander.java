package com.mart.events.commander;

import com.mart.events.command.CreatePerson;
import com.mart.events.event.PersonCreated;
import com.mart.events.model.PersonData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
@Slf4j
public class PersonCommander {
    CommandGateway gateway;

    public UUID createPerson(PersonData data){
        UUID uuid = UUID.randomUUID();
        CreatePerson createPerson = new CreatePerson(uuid, data.getDateOfBirth());
        log.info("Dispatched Command createPerson");

        gateway.sendAndWait(createPerson);

        return uuid;
    }
}
