package com.mart.transactions.services;

import com.mart.transactions.commands.BookHotelCommand;
import com.mart.transactions.commands.BookRentalCommand;
import com.mart.transactions.commands.Status;
import com.mart.transactions.events.HotelBookedEvent;
import com.mart.transactions.events.RentalBookedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class RentalService {
    private final Random random = new Random();
    private final EventGateway eventGateway;

    @CommandHandler
    public void bookRental(BookRentalCommand rentalCommand) throws InterruptedException {
        log.info("Service rental event");
        //Thread.sleep(random.nextInt(11) * 1000);
        Status state = random.nextInt(6) == 5 ? Status.DECLINED: Status.ACCEPTED;
        eventGateway.publish(new RentalBookedEvent(rentalCommand.getId(), rentalCommand.getTravelId(), state));
    }
}
