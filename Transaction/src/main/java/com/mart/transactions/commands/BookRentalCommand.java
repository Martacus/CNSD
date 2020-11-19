package com.mart.transactions.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class BookRentalCommand {

    @TargetAggregateIdentifier
    UUID id;
    UUID travelId;
}

