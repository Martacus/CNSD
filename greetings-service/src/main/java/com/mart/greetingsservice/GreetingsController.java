package com.mart.greetingsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    final
    GreetingsService service;

    public GreetingsController(GreetingsService service) {
        this.service = service;
    }

    @GetMapping("greet")
    public String getGreet(){
        return service.getGreet();
    }


}
