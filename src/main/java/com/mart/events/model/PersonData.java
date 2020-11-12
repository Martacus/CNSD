package com.mart.events.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class PersonData {
    private UUID uuid;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;

}
