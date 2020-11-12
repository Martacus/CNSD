package com.mart.events.command;

import lombok.Value;

import java.time.LocalDate;

@Value
public class RegisterDateOfDeath {

    LocalDate dateOfDeath;
}
