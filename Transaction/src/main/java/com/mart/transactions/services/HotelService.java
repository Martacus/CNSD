package com.mart.transactions.services;

import com.mart.transactions.commands.BookFlightCommand;
import com.mart.transactions.commands.BookHotelCommand;
import com.mart.transactions.commands.Status;
import com.mart.transactions.events.FlightBookedEvent;
import com.mart.transactions.events.HotelBookedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelService {
    private final Random random = new Random();
    private final EventGateway eventGateway;

    @CommandHandler
    public void bookHotel(BookHotelCommand bookHotel) throws InterruptedException {
        log.info("Service hotel event");
        //Thread.sleep(random.nextInt(11) * 1000);
        Status state = random.nextInt(6) == 5 ? Status.DECLINED: Status.ACCEPTED;
        eventGateway.publish(new HotelBookedEvent(bookHotel.getId(), bookHotel.getTravelId(), state));
    }
}
