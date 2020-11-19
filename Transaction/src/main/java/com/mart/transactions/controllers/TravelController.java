package com.mart.transactions.controllers;

import com.mart.transactions.commanders.CreateTravelCommander;
import com.mart.transactions.models.TravelDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController()
@RequestMapping("/travel")
@Slf4j
public class TravelController {

    @Autowired
    private CreateTravelCommander createTravelCommander;

    @PostMapping
    public UUID createTravel(@RequestBody TravelDTO dto){
        log.info("Post create travel");
        return createTravelCommander.createTravel(dto);
    }
}
