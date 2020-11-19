package com.mart.transactions.events;

import com.mart.transactions.commands.Status;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class HotelBookedEvent {
    UUID hotelId;
    UUID travelId;
    Status status;
}
