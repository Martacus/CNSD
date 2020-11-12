package com.mart.events.event;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
@AllArgsConstructor
public class DeathDateRegisteredEvent {

    UUID id;
    LocalDate dateOfDeath;
}
