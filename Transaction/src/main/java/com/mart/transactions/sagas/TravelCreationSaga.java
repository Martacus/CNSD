package com.mart.transactions.sagas;

import com.mart.transactions.commands.BookFlightCommand;
import com.mart.transactions.commands.BookHotelCommand;
import com.mart.transactions.commands.BookRentalCommand;
import com.mart.transactions.commands.Status;
import com.mart.transactions.events.FlightBookedEvent;
import com.mart.transactions.events.HotelBookedEvent;
import com.mart.transactions.events.RentalBookedEvent;
import com.mart.transactions.events.TravelCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@Slf4j
public class TravelCreationSaga {

    @Autowired
    private transient CommandGateway gateway;

    private Status flightStatus;
    private Status hotelStatus;
    private Status rentalStatus;
    private Status travelStatus;

    @StartSaga
    @SagaEventHandler(associationProperty = "travelId")
    public void handle(TravelCreatedEvent event){
        log.info("Saga Invoked");

        UUID flightId = UUID.randomUUID();
        UUID rentalId = UUID.randomUUID();
        UUID hotelId = UUID.randomUUID();

        BookFlightCommand flightCommand = new BookFlightCommand(flightId, event.getTravelId());
        BookRentalCommand rentalCommand = new BookRentalCommand(rentalId, event.getTravelId());
        BookHotelCommand hotelCommand = new BookHotelCommand(hotelId, event.getTravelId());

        SagaLifecycle.associateWith("flightId", flightId.toString());
        SagaLifecycle.associateWith("rentalId", rentalId.toString());
        SagaLifecycle.associateWith("hotelId", hotelId.toString());

        gateway.send(flightCommand);
        gateway.send(rentalCommand);
        gateway.send(hotelCommand);
        log.info("commands send");
    }

    @SagaEventHandler(associationProperty = "flightId")
    public void handle(FlightBookedEvent flightBooked) {
        log.info("Saga flight booked");
        this.flightStatus = flightBooked.getStatus();
        if (hotelStatus != null && rentalStatus != null && flightStatus != null) {
            finishTravel();
        }
    }

    @SagaEventHandler(associationProperty = "rentalId")
    public void handle(RentalBookedEvent rentalBooked) {
        log.info("Saga rental booked");
        this.rentalStatus = rentalBooked.getStatus();
        if (hotelStatus != null && rentalStatus != null && flightStatus != null) {
            finishTravel();
        }
    }

    @SagaEventHandler(associationProperty = "hotelId")
    public void handle(HotelBookedEvent hotelBooked) {
        log.info("Saga hotel booked");
        this.hotelStatus = hotelBooked.getStatus();
        if (hotelStatus != null && rentalStatus != null && flightStatus != null) {
            finishTravel();
        }
    }

    private void finishTravel() {
        log.info("Finish travel");
        if (hotelStatus == Status.ACCEPTED && flightStatus == Status.ACCEPTED && rentalStatus == Status.ACCEPTED) {
            travelStatus = Status.ACCEPTED;
        } else {
            travelStatus = Status.DECLINED;
        }
        log.info("Travel state: {}", travelStatus);
        SagaLifecycle.end();
    }
}
