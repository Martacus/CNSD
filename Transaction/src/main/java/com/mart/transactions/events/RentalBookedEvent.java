package com.mart.transactions.events;

import com.mart.transactions.commands.Status;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class RentalBookedEvent {
    UUID rentalId;
    UUID travelId;
    Status status;
}
