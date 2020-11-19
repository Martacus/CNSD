package com.mart.transactions.commanders;

import com.mart.transactions.commands.CreateTravelCommand;
import com.mart.transactions.models.TravelDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class CreateTravelCommander {
    private CommandGateway gateway;

    public UUID createTravel(TravelDTO dto){
        UUID uuid = UUID.randomUUID();
        CreateTravelCommand createTravelCommand = new CreateTravelCommand(uuid, dto.getName(), dto.getEmail());
        gateway.sendAndWait(createTravelCommand);
        log.info("Travel created");
        return uuid;
    }
}
