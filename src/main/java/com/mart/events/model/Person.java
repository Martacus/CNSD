package com.mart.events.model;

import com.mart.events.command.CreatePerson;
import com.mart.events.command.RegisterDateOfDeath;
import com.mart.events.event.DeathDateRegisteredEvent;
import com.mart.events.event.PersonCreated;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDate;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
@Slf4j
public class Person {
    @AggregateIdentifier
    public UUID id;

    public LocalDate dateOfBirth;
    public LocalDate dateOfDeath;

    @CommandHandler
    public Person(CreatePerson createPerson) {
        PersonCreated created = new PersonCreated(createPerson.getId(), createPerson.getDateOfBirth());
        AggregateLifecycle.apply(created);
        log.info("Person Constructor");
    }

    @CommandHandler
    public void registerDeath(RegisterDateOfDeath registerDateOfDeath){
        if(registerDateOfDeath.getDateOfDeath().isBefore(dateOfBirth)){
            throw new IllegalStateException();
        }

        DeathDateRegisteredEvent created = new DeathDateRegisteredEvent(this.id, registerDateOfDeath.getDateOfDeath());
        AggregateLifecycle.apply(created);
    }

    @EventSourcingHandler
    public void handlePersonCreated(PersonCreated event){
        log.info("Handling the event");
        this.id = event.getId();
        this.dateOfBirth = event.getDateOfBirth();
    }

    @EventSourcingHandler
    public void handleDeathdateRegistered(DeathDateRegisteredEvent event){
        log.info("Handling the death event");
        this.dateOfDeath = event.getDateOfDeath();
    }

}
