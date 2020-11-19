package com.mart.transactions.events;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class TravelCreatedEvent {
    UUID travelId;
    String name;
    String email;
}
