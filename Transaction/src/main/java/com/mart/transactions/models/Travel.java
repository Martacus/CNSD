package com.mart.transactions.models;

import com.mart.transactions.commands.CreateTravelCommand;
import com.mart.transactions.events.TravelCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
@Slf4j
public class Travel {

    @AggregateIdentifier
    public UUID id;

    public String name;
    public String email;

    @CommandHandler
    public Travel(CreateTravelCommand command){
        this.id = command.getId();
        TravelCreatedEvent travelCreatedEvent = new TravelCreatedEvent(command.getId(), command.getName(), command.getEmail());
        AggregateLifecycle.apply(travelCreatedEvent);
        log.info("TravelCreatedEvent initialized and send.");
    }

    @EventSourcingHandler
    protected void on(TravelCreatedEvent event){
        this.name = event.getName();
        this.email = event.getEmail();
    }

}
