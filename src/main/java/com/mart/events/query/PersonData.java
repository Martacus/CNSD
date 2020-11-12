package com.mart.events.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
public class PersonData {
    @Id
    UUID id;

    @Column(nullable = false)
    LocalDate dateOfBirth;

    public PersonData() {

    }
}
